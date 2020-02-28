# IA_UR
La Semi-IA que j'ai codé, avec des explications sur son fonctionnement !

## Installation

Deux techniques.
1. La simple manipulation
```bash 
Project Structure > Module > Dependancies > add
```
et ajouter le fichier .jar.

2. Si vous utilisez Gradle, créez un dossier "libs" et ajoutez ce morceau de code dans le fichier build.gradle : 

```c
dependencies {
    compile "net.dv8tion:JDA:4.1.1_107"
    compile "org.slf4j:slf4j-nop:1.7.25"
    compile files('libs/IA-UR.jar')
}
```
