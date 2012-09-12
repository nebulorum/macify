Macify
==================

This is a simple cross platform wrapper to the Java com.apple.eawt version 1.6 API.

The idea is to provide a Mac independent library that can be compile on other platforms and included in projects.
Code that sets handlers in the com.apple.eawt.Application can be defined on a non Mac machine, compiled, and packaged.

As long as the code path that defines the handlers is not executed on non Mac machines the code will not interfere with
normal execution. On the Mac the wrappers will setup the required handlers correctly.

The adapters and wrappers handle the following events:

* Application Quit (Command-Q)
* Application About
* Application Preferences

A simple utility static method is provided to verify if the code is executing on a Mac or not. A simplified usage:

    if (Macify.isMac()) {
      Macify.getApplication().setQuitHandler(...);
    }

