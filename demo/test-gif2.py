from __future__ import division # Just because division doesn't work right in 2.7.4
from Tkinter import *
from PIL import Image,ImageTk
import threading
from time import sleep

def anim_gif(name):
    ## Returns { 'frames', 'delay', 'loc', 'len' }
    im = Image.open(name)
    gif = { 'frames': [],
            'delay': 100,
            'loc' : 0,
            'len' : 0 }
    pics = []
    try:
        while True:
            pics.append(im.copy())
            im.seek(len(pics))
    except EOFError: pass

    temp = pics[0].convert('RGBA')
    gif['frames'] = [ImageTk.PhotoImage(temp)]
    temp = pics[0]
    for item in pics[1:]:
        temp.paste(item)
        gif['frames'].append(ImageTk.PhotoImage(temp.convert('RGBA')))

    try: gif['delay'] = im.info['duration']
    except: pass
    gif['len'] = len(gif['frames'])
    return gif

def ratio(a,b):
    if b < a: d,c = a,b
    else: c,d = a,b
    if b == a: return 1,1
    for i in reversed(xrange(2,int(round(a / 2)))):
        if a % i == 0 and b % i == 0:
            a /= i
            b /= i
    return (int(a),int(b))

class App(Frame):
    def show(self,image=None,event=None):
        self.display.create_image((0,0),anchor=NW,image=image)   

    def animate(self,event=None):
        self.show(image=self.gif['frames'][self.gif['loc']])
        self.gif['loc'] += 1
        if self.gif['loc'] == self.gif['len']:
            self.gif['loc'] = 0
        if self.cont:
            threading.Timer((self.gif['delay'] / 1000),self.animate).start()

    def kill(self,event=None):
        self.cont = False
        sleep(0.1)
        self.quit()

    def __init__(self,master):
        Frame.__init__(self,master)
        self.grid(row=0,sticky=N+E+S+W)
        self.rowconfigure(1,weight=2)
        self.rowconfigure(3,weight=1)
        self.columnconfigure(0,weight=1)
        self.title = Label(self,text='No title')
        self.title.grid(row=0,sticky=E+W)
        self.display = Canvas(self)
        self.display.grid(row=1,sticky=N+E+S+W)
        self.user = Label(self,text='Posted by No Username')
        self.user.grid(row=2,sticky=E+W)
        self.comment = Text(self,height=4,width=40,state=DISABLED)
        self.comment.grid(row=3,sticky=N+E+S+W)
        self.cont = True
        self.gif = anim_gif('test.gif')
        self.animate()

        root.protocol("WM_DELETE_WINDOW",self.kill)


root = Tk()
root.rowconfigure(0,weight=1)
root.columnconfigure(0,weight=1)
app = App(root)
app.mainloop()

try: root.destroy()
except: pass
