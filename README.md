# OpenToonix RTMP Server

RTMP (Real-Time Messaging Protocol) server emulator
for Toonix World _(mostly known as **Mundo Toonix**)_.

## Requirements

- **Java -** Version 15
- [Red5 Server](https://github.com/Red5/red5-server) instance

## Building

To build the project,
you need to have [Maven](https://maven.apache.org/)
installed on your computer.

Once you have Maven installed,
you can run the following command to build the app:

```shell
mvn clean package
```

## Running

To run the app, you need to build it or download
[the latest release](https://github.com/OpenToonix/OpenToonix-RTMP-Server/releases).

Once you have the app built or downloaded,
you have to move the `opentoonix-rtmp-server-*.war` file
to the `webapps` folder of your Red5 Server instance.

Then, you can start the Red5 Server instance
and the app will be running.

## Features

You can check the current state of features [here](https://github.com/OpenToonix/OpenToonix-RTMP-Server/wiki/Features).

## Support

For further support, you can join us on [Discord](https://discord.gg/8ZWkyXnv4h).
