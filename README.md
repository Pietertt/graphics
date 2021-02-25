# Amazon simulatie

Even voor de handigheid een kleine handleiding. Voeg vooral iets toe als je het niet snapt.

## Installatie

Pull eerst de repo met deze commands.

```bash
git init
git remote add origin https://github.com/Pietertt/graphics.git
git pull origin master
```

Om de javascript bestanden te genereren is het nodig om het project eerst te builden.

```bash
make build
```

Of 
```bash
cd src/main/resources/static
npm install
npm run build
```

Daarna kan het project opgestart worden met 
```bash
make run
```

Of
```bash
./mvnw spring-boot:run
```


## Git commands

Een nieuwe branch maken

```bash
git branch <naam van je branch>
```

Naar de branch toe gaan

```bash
git checkout <naam van je branch>
```

Bestanden toevoegen

```bash
git add <bestandsnaam> (--all voor het toevoegen van alle bestanden)
```

Je veranderingen committen

```bash
git commit -m "<beschrijving>"
```

Je veranderingen pushen

```bash
git push origin <je branchnaam>
```
