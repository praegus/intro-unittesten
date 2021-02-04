# Lokaal clonen van deze repo
Stap 1 is om de code lokaal neer te zetten. Volg daarvoor de volgende stappen:
* `git pull https://github.com/praegus/intro-unittesten/`
* `git checkout run-with-docker`
* `cd docker`

# Draaien testobject via Docker
Stap 2 is om vervolgens de Docker container op te starten.

Om het testobject te draaien heb je alleen een Docker-installatie nodig.

* Windows: Docker for Windows (Docker toolbox wordt niet gesupport)
* MacOS: Docker for Mac

Als het goed is zit je nu in de directory `docker` (zie stap 1 hierboven).
Draai vervolgens het run.bat (Windows) of run.sh script in een console en de docker image wordt gebouwd en de container wordt vervolgens gestart.

Windows:
```
run.bat
```

Mac
```
chmod +x run.sh
./run.sh
```
