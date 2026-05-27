Qt6 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://git.openembedded.org/openembedded-core/

URI: https://git.openembedded.org/meta-openembedded/

Yocto version support
---------------------

The branching of meta-qt6 layer follows [Qt branching scheme](https://wiki.qt.io/Branch_Guidelines),
that is `dev` for the development branch, `6.x` for minor stabilization branches,
and `6.x.y` for the release branches. Additional branches are available for commercial users
with `lts-6.x.y` for LTS (Long Term Support) releases and `esm-6.x` for the ESM
(Extended Security Maintenance) patches.

Each Qt release is tagged either as `v6.x.y` or `v6.x.y-lts`

The following table shows the Yocto releases that are used to test each
Qt version (x) and any additional Yocto releases that are stated in
the LAYERSERIES_COMPAT (c).

| Yocto \ Qt | dev | 6.11 | 6.10 | 6.9 | 6.8 | 6.7 | 6.6 | 6.5 | 6.4 | 6.3 | 6.2 |
|:---------- |:---:|:----:|:----:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| wrynose    |  x  |  x   |      |     |  x  |     |     |     |     |     |     |
| whinlatter |  x  |  x   |  c   |  c  |  x  |     |     |     |     |     |     |
| walnascar  |  c  |  c   |  c   |  c  |  c  |     |     |     |     |     |     |
| styhead    |  c  |  c   |  c   |  c  |  c  |  c  |     |     |     |     |     |
| scarthgap  |  x  |  x   |  c   |  c  |  x  |  c  |  c  |  x  |     |     |     |
| nanbield   |     |      |  c   |  c  |  c  |  c  |  c  |  c  |     |     |     |
| mickledore |     |      |  c   |  c  |  c  |  c  |  c  |  c  |  c  |     |     |
| langdale   |     |      |  c   |  c  |  c  |  c  |  c  |  c  |  c  |     |     |
| kirkstone  |     |      |  c   |  c  |  x  |  c  |  c  |  c  |  c  |  c  |  c  |
| honister   |     |      |      |     |     |     |  c  |  c  |  c  |  c  |  c  |
| hardknott  |     |      |      |     |     |     |  c  |  c  |  c  |  c  |  c  |
| gatesgarth |     |      |      |     |     |     |  c  |  c  |  c  |  c  |  c  |
| dunfell    |     |      |      |     |     |     |  c  |  c  |  c  |  c  |  c  |
|            |     |      |      |     |     |     |     |     |     |     |     |
|            | dev |stable|closed| EOS | LTS | EOS | EOS | ESM | EOS | EOS | EOS |

For more details about Qt versions, go to https://doc.qt.io/qt-6/qt-releases.html

Commercial Qt
-------------

Qt is dual-licensed under commercial and open source licenses.
The license can be selected using the `QT_EDITION` variable. `commercial` and
`opensource` are valid values. The default value is `opensource`.

For commercial Qt users, the layer provides additional support with LTS
(Long Term Support) releases, ESM (Extended Security Maintenance) patches, and
additional Qt modules licensed as commercial-only.

The LTS releases are available in branches named `lts-6.x.y` and tagged as
`v6.x.y-lts`. The ESM patches are available in branches named `esm-6.x`.

The source code for the LTS releases, the ESM patches, and the commercial Qt modules
are only available for commercial Qt license holders. They can only be built and
used if you have a commercial Qt license and you have set up SSH access to
Qt Gerrit (see links below).

The commercial Qt modules are included in the build if the `QT_COMMERCIAL_MODULES`
variable is set to `1` and you are using a commercial edition of Qt.

QtMultimedia
------------

Qt Multimedia now prefers [FFmpeg][1] as the multimedia backend instead of GStreamer.
FFmpeg recipe, however, is flagged with LICENSE_FLAGS = "commercial", which means
that user must accept the license before FFmpeg can be used in the build. If user
accepts the license using LICENSE_FLAGS_ACCEPTED = 'commercial_ffmpeg', the FFmpeg
support is enabled in Qt Multimedia. If user doesn't accept the license,
Qt Multimedia only uses GStreamer.

[1]: https://doc.qt.io/qt-6/qtmultimedia-index.html#ffmpeg-as-the-default-backend

Contributing
------------

To contribute to this layer submit the patches for review using
[Qt Gerrit](https://codereview.qt-project.org).

More information about Qt Gerrit and how to use it:
 - [Gerrit_Introduction](https://wiki.qt.io/Gerrit_Introduction)
 - [Setting_up_Gerrit](https://wiki.qt.io/Setting_up_Gerrit)

Report bugs on [Qt Bug Tracker](https://bugreports.qt.io) using
`Yocto: meta-qt6 layer` component.

Layer maintainers
-----------------

 - Qt Embedded Linux team <metaqt6@qt.io>

