from Tkinter import *

def run_animation():
    while True:
        try:
            global photo
            global frame
            global label
            photo = PhotoImage(
                file = photo_path,
                format = "gif - {}".format(frame)
                )

            label.configure(image = nextframe)

            frame = frame + 1

        except Exception:
            frame = 1
            break

root = Tk()
photo_path = "/home/zehl/Downloads/demo/test.gif"

photo = PhotoImage(
    file = photo_path,
    )
label = Label(
    image = photo
    )
animate = Button(
    root,
    text = "animate",
    command = run_animation
    )

label.pack()
animate.pack()

root.mainloop()
