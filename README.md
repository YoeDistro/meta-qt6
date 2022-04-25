Qt6 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://git.openembedded.org/openembedded-core/

and optionally:

URI: https://git.openembedded.org/meta-openembedded/
URI: https://git.openembedded.org/meta-python2

Yocto version support
---------------------

The branching of meta-qt6 layer follows Qt branching scheme, that is
`dev` for the development branch, `6.x` for each minor release and
`lts-6.x` for commercial LTS releases.

Following table shows the Yocto branches which are used to test each
Qt version (x) and any additional Yocto releases that are stated in
the LAYERSERIES_COMPAT (c).

| Yocto \ Qt | 6.2 | 6.1 | 6.0 |
| ---------- |:---:|:---:|:---:|
| master     |  x  |     |     |
| honister   |  x  |     |     |
| hardknott  |  x  |  x  |  x  |
| gatesgarth |  c  |  x  |  x  |
| dunfell    |  x  |  x  |  x  |

Commercial Qt
-------------

Qt is dual-licensed under commercial and open source licenses.
The license can be selected using the `QT_EDITION` variable. `commercial` and
`opensource` are valid values. The default value is `opensource`.

For commercial Qt users, the layer provides LTS (Long Term Support) releases
for selected Qt versions. These are available in branches named `lts-6.x`.
The LTS versions can only be built and used if you have a commercial Qt license
and you have set up SSH access to Qt Gerrit (see links below).

QtWebEngine
-----------

QtWebEngine needs at least CMake 3.19, which is available starting from Hardknott.
For this reason QtWebEngine is not tested on older releases.

Contributing
------------

To contribute to this layer you should submit the patches for review using
[Qt Gerrit](https://codereview.qt-project.org).

More information about Qt Gerrit and how to use it:
 - https://wiki.qt.io/Gerrit_Introduction
 - https://wiki.qt.io/Setting_up_Gerrit

Layer maintainers
-----------------

 - Samuli Piippo <samuli.piippo@qt.io>

