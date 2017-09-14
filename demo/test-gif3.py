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
            gif['frames'].append(im.copy())
            im.seek(len(gif['frames']))
    except EOFError: pass

    try: gif['delay'] = im.info['duration']
    except: pass
    gif['len'] = len(gif['frames'])
    return gif

class App(Frame):
    def show(self,image=None,event=None):
        can_w = self.display['width']
        can_h = self.display['height']

        pic_w,pic_h = image.size
        rat_w,rat_h = ratio(pic_w,pic_h)

        while pic_w > int(can_w) or pic_h > int(can_h):
            pic_w -= rat_w
            pic_h -= rat_h

        resized = image.resize((pic_w,pic_h))
        resized = ImageTk.PhotoImage(resized)
        self.display.create_image((0,0),anchor=NW,image=resized)   
