# Duo Card Game Implementation with Java

This repository provides an design and implementation of Duo Card game with Java using Mediator design pattern
and object-oriented design principles. UML class diagram of implementation is also provided under the DuoCardGameApplication project directory named as `Duo_Card_Game_Application_Class_Diagram.pdf`. 

Concepts fulfilled in this application are as follows: 
- Object Oriented Analysis and Design
- Object Orientation Fundamentals
- Inheritence, Polymorphism, Abstract Classes, Interfaces
- Collections
- UML Class Diagrams
- Mediator Design Patter
- CSV file I/O
- Javadoc

Details of Requirements for the project are given in the `CENG431-Spring2025_HW01.pdf` file under this repository.

Project structure is as follows:

```bash
|   Duo Card Game Application.pdf
|
\---DuoCardGameApplication
    +---.vscode
    |       settings.json
    |
    +---bin
    |   |   game_status.csv
    |   |
    |   +---app
    |   |       CardGameMediator.class
    |   |       DuoCardGameApp.class
    |   |       package-info.class
    |   |       Player.class
    |   |
    |   +---cards
    |   |       ActionCard.class
    |   |       Card.class
    |   |       DrawTwoCard.class
    |   |       NumberCard.class
    |   |       package-info.class
    |   |       ReverseCard.class
    |   |       ShuffleHandsCard.class
    |   |       SkipCard.class
    |   |       WildCard.class
    |   |       WildDrawFourCard.class
    |   |
    |   \---helpers
    |           Color.class
    |           FileIO.class
    |           package-info.class
    |
    +---doc
    |   |   allclasses-index.html
    |   |   allpackages-index.html
    |   |   copy.svg
    |   |   element-list
    |   |   help-doc.html
    |   |   index-all.html
    |   |   index.html
    |   |   link.svg
    |   |   member-search-index.js
    |   |   module-search-index.js
    |   |   overview-summary.html
    |   |   overview-tree.html
    |   |   package-search-index.js
    |   |   script.js
    |   |   search-page.js
    |   |   search.html
    |   |   search.js
    |   |   stylesheet.css
    |   |   tag-search-index.js
    |   |   type-search-index.js
    |   |
    |   +---app
    |   |       CardGameMediator.html
    |   |       DuoCardGameApp.html
    |   |       DuoGardGameApp.html
    |   |       package-summary.html
    |   |       package-tree.html
    |   |       Player.html
    |   |
    |   +---cards
    |   |       ActionCard.html
    |   |       Card.html
    |   |       DrawTwoCard.html
    |   |       NumberCard.html
    |   |       package-summary.html
    |   |       package-tree.html
    |   |       ReverseCard.html
    |   |       ShuffleHandsCard.html
    |   |       SkipCard.html
    |   |       WildCard.html
    |   |       WildDrawFourCard.html
    |   |
    |   +---helpers
    |   |       Color.html
    |   |       FileIO.html
    |   |       package-summary.html
    |   |       package-tree.html
    |   |
    |   +---legal
    |   |       COPYRIGHT
    |   |       jquery.md
    |   |       jqueryUI.md
    |   |       LICENSE
    |   |
    |   +---resources
    |   |       glass.png
    |   |       x.png
    |   |
    |   \---script-dir
    |           jquery-3.6.1.min.js
    |           jquery-ui.min.css
    |           jquery-ui.min.js
    |
    \---src
        |   game_status.csv
        |
        +---app
        |       CardGameMediator.java
        |       DuoCardGameApp.java
        |       package-info.java
        |       Player.java
        |
        +---cards
        |       ActionCard.java
        |       Card.java
        |       DrawTwoCard.java
        |       NumberCard.java
        |       package-info.java
        |       ReverseCard.java
        |       ShuffleHandsCard.java
        |       SkipCard.java
        |       WildCard.java
        |       WildDrawFourCard.java
        |
        \---helpers
                Color.java
                FileIO.java
                package-info.java
```

`DuoCardGameApp` class contains main method to run the simulation. Details of classes and their methods are well documented
with Javadoc as an HTML file which can be found under the `doc` folder named as `index-all.html`. When simulation is ended it creates `game_status.csv` file containing winner of each round and winner of the game at the end along with players gained points. Overall flow of game is displayed in the terminal.
