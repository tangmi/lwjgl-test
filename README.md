lwjgl-test
==========

this project will hopefully eventually become a generic 3d game framework agnostic of the style of gameplay it is used to power.

this framework will likely include:

* a graphics display engine containing default shaders (theoretically abstracting all OpenGL out of the client code)
* module-based framework where various core and extra functionality can be dynamically loaded and unloaded
* a wavefront .obj loader and renderer
* built-in level editor (entity placement/level logic, etc)
* demos showcase use cases in various gameplay styles (fps, 3rd person, spaceship, etc)
* maybe a super simple voxel-styled engine module for people to quickly make a game without spending too much time creating assets (this would be directed at programmer types, primarily at hackathons)

![hicole](http://i.imgur.com/0RMfM0N.png)

![screenshot](http://i.imgur.com/JGarOES.png)

![screenshot2](http://i.imgur.com/UkNWkFv.png)

![screenshot3](http://i.imgur.com/Ttl5Wgg.png)

to set up eclipse, follow [this guide](https://github.com/tangmi/environment-setup), ignoring any slick2d reference (you only need to add the LWJGL libraries and the slick-util library)

lol [@aaasen](https://github.com/aaasen/voxel-party/)

also, this project will likely be renamed to something better than "lwjgl-test"