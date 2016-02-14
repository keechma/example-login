# keechma-login

This application shows how to use the [Keechma framework](http://github.com/keechma/keechma)
to handle the login workflow.

It achieves that by splitting the application in two apps:

- Session app - Takes care of the login
- Main app - Implements the application logic

When the user is logged in the session app will mount the main application inside
itself - at that moment, two applications will be running at once. Each application
will have it's own app state and run completely independently from the other.

This is possible because there are no shared globals in Keechma.

Read the [annotated source](http://keechma.com/annotated/login.html).

## Setup

Make sure you have [Leiningen](http://leiningen.org/) installed.

Clone the repo:

```
$ git clone https://github.com/keechma/keechma-todomvc.git
$ cd keechma-todomvc
```

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL.

## License

Copyright © 2016 Mihael Konjevic

Distributed under the MIT License.# keechma-login
