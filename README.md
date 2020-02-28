# IA_UR
La Semi-IA que j'ai codé, avec des explications sur son fonctionnement !

Pour l'ajouter à un projet vous pouvez aller dans Project Structure > Module > Dependancies > add ; et ajouter le .jar
Si vous utilisez gradle, créez un dossier libs dans le dossier de votre projet, et ajoutez ce morceau de code dans le build.gradle :

dependencies {
    compile "net.dv8tion:JDA:4.1.1_107"
    compile "org.slf4j:slf4j-nop:1.7.25"
    compile files('libs/IA-UR.jar')
}
