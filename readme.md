# MongoDB installation guild on MacOS


* brew install mongodb-community@4.2


In addition to the binaries, the install creates:

- the configuration file (/usr/local/etc/mongod.conf)
- the log directory path (/usr/local/var/log/mongodb)
- the data directory path (/usr/local/var/mongodb)


To run MongoDB (i.e. the mongod process) as a macOS service, issue the following:

```
brew services start mongodb-community@4.2
```
