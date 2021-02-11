Qt6 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://github.com/openembedded/openembedded-core

and optionally:

URI: https://github.com/openembedded/meta-openembedded

Yocto version support
---------------------

The branching of meta-qt6 layer follows Qt branching scheme, that is
`dev` for the development branch, and `6.x` for each minor release.

Following table shows the Yocto branches which are used to test each
Qt version.

| Yocto \ Qt | dev | 6.1 | 6.0 |
| ---------- |:---:|:---:|:---:|
| master     |  x  |  x  |  x  |
| gatesgarth |  x  |  x  |  x  |
| dunfell    |  x  |  x  |  x  |

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

