Qt6 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://git.openembedded.org/openembedded-core/

and optionally:

URI: https://git.openembedded.org/meta-openembedded/

Yocto version support
---------------------

The branching of meta-qt6 layer follows [Qt branching scheme](https://wiki.qt.io/Branch_Guidelines),
that is `dev` for the development branch, `6.x` for minor stabilization branches,
`6.x.y` for the release branches and `lts-6.x.y` for commercial LTS releases.

Each Qt release is tagged either as `v6.x.y` or `v6.x.y-lts`

Following table shows the Yocto branches which are used to test each
Qt version (x) and any additional Yocto releases that are stated in
the LAYERSERIES_COMPAT (c).

| Yocto \ Qt | dev | 6.6 | 6.5 | 6.4 | 6.3 | 6.2 |
| ---------- |:---:|:---:|:---:|:---:|:---:|:---:|
| master     |  x  |  x  |  x  |     |     |     |
| mickledore |  x  |  x  |  x  |  x  |     |     |
| langdale   |  x  |  x  |  x  |  x  |     |     |
| kirkstone  |  x  |  x  |  x  |  x  |  x  |  x  |
| honister   |  c  |  c  |  c  |  c  |  x  |  x  |
| hardknott  |  c  |  c  |  c  |  c  |  c  |  c  |
| gatesgarth |  c  |  c  |  c  |  c  |  c  |  c  |
| dunfell    |  x  |  x  |  x  |  x  |  x  |  x  |

Commercial Qt
-------------

Qt is dual-licensed under commercial and open source licenses.
The license can be selected using the `QT_EDITION` variable. `commercial` and
`opensource` are valid values. The default value is `opensource`.

For commercial Qt users, the layer provides additional support with LTS
(Long Term Support) releases for selected Qt versions and additional
Qt modules licensed as commercial-only.

The LTS releases are available in branches named `lts-6.x`. The source code
for the LTS releases and the commercial Qt modules are only available for
commercial Qt license holders. They can only be built and used if you have
a commercial Qt license and you have set up SSH access to Qt Gerrit (see links below).

The commercial Qt modules are included in the build if the `QT_COMMERCIAL_MODULES`
variable is set to `1` and you are using a commercial edition of Qt.

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

