var DEBUG                   = false;

var indexCharacterClass = [];
var mapCharacterClass = [];

var mapTiles = null;

var DOWN                    = 0;
var UP                      = 2;
var LEFT                    = 1;
var RIGHT                   = 3;

var NUMBER_TILES_VERTICAL   = 15;
var NUMBER_TILES_HORIZONTAL = 30;
var SIZE_TILES_VERTICAL     = 48;
var SIZE_TILES_HORIZONTAL   = 32;

var EMPTY_TILE              = -1;
var PLAYER_TILE             = 100;

var PLAYER_TIME_TO_CHANGE_TILE  = 200;
var FRAMERATE               = 25;

var player = null

var nextPlayerId = 0;

var mapOfPlayers = [];

function getNextPlayerId() {
    var result = nextPlayerId;
    nextPlayerId++;
    return result;
}

function CharacterClass(name, fileName) {
    this.name = name;
    this.fileName = fileName;
}

function Player(name, characterClassName) {



    this.name = name;
    this.characterClassName = characterClassName;
    this.orientation = 0;

    this.x = -1;
    this.y = -1;

    this.register = function() {
        this.id = getNextPlayerId();
        mapOfPlayers[this.id] = this;
    }

    this.getGraphics = function() {
        return $("#player-"+this.id)
    }

    if(indexCharacterClass.indexOf(characterClassName) == -1) {
        alert("<configuration problem: no characterClassName for\""+characterClassName+"\">")
    }
    else {
        this.characterClass = mapCharacterClass[characterClassName];
    }

    this.setPosition = function(x,y) {

        if(mapTiles[x] && mapTiles[x][y] && mapTiles[x][y] == EMPTY_TILE) {

            if(mapTiles[this.x] && mapTiles[this.x][this.y]) {
                mapTiles[this.x][this.y] == EMPTY_TILE;
            }
            this.x = x;
            this.y = y;

            mapTiles[x][y] = PLAYER_TILE+this.id;
        }

    }

    this.display = function() {
        var params = [{id:this.id, name:name, className:characterClassName}]
        $( "#playerTemplate" ).tmpl(params).appendTo("#scene");
        this.getGraphics().css("background", "url("+mapCharacterClass[ this.characterClassName].fileName+") 0 0 no-repeat");
        this.setOrientation(0);

        this.getGraphics().css("left",  this.x*SIZE_TILES_HORIZONTAL+"px");
        this.getGraphics().css("top",   this.y*SIZE_TILES_VERTICAL+"px");

        console.log("player <"+name+"> is displayed");
    }

    function adaptOrientation(num) {

        if(num == 0) {
            return 0;
        }
        if(num == 1) {
            return 1;
        }
        if(num == 2) {
            return 3;
        }

        if(num == 3) {
            return 2;
        }

    }

    this.askSingleMove = function(orientation) {
        var dx = 0;
        var dy = 0;

        this.setOrientation(orientation)
        var correctedOrientation = adaptOrientation(orientation)

        switch (orientation) {
            case 0:
                dy=1;
                break;
            case 1:
                dx=-1;
                break;
            case 2:
                dy=-1;
                break;
            case 3:
                dx=1;
                break;
        }

        // function that is in the body of the main html file
        if(!(dx==0 && dy==0))
        canYouConfirmThisMove(this.id, this.x, this.y, dx, dy);
    }



    this.makeSingle = function(x_, y_) {


        var dx = x_ - this.x;
        var dy = y_ - this.y;

        mapTiles[this.x][this.y] = EMPTY_TILE;

        this.x += dx;
        this.y += dy;

        if(this.x<0) {
            this.x = 0;
        }
        if(this.x>=NUMBER_TILES_HORIZONTAL) {
            this.x = NUMBER_TILES_HORIZONTAL;
        }

        if(this.y<0) {
            this.y = 0;
        }
        if(this.y>=NUMBER_TILES_VERTICAL) {
            this.y = NUMBER_TILES_VERTICAL;
        }

        mapTiles[this.x][this.y] = PLAYER_TILE+this.id;

        var timer = this.doWalking();
        var linkToPlayer = this;

        this.getGraphics().animate({
            left: '+='+(dx*SIZE_TILES_HORIZONTAL),
            top: '+='+(dy*SIZE_TILES_VERTICAL)
        }, PLAYER_TIME_TO_CHANGE_TILE, "linear", function(){
            linkToPlayer.stopWalking(timer);
        })

    }

    this.singleMove = function(orientation) {
        this.askSingleMove(orientation);
    }


//    this.singleMove = function(orientation) {
//        var dx = 0;
//        var dy = 0;
//
//        this.setOrientation(orientation)
//        var correctedOrientation = adaptOrientation(orientation)
//
//        switch (orientation) {
//            case 0:
//                dy=1;
//                break;
//            case 1:
//                dx=-1;
//                break;
//            case 2:
//                dy=-1;
//                break;
//            case 3:
//                dx=1;
//                break;
//        }
//
//
//        if(mapTiles[this.x+dx] && mapTiles[this.x+dx][this.y+dy] && mapTiles[this.x+dx][this.y+dy] == EMPTY_TILE) {
//
//            mapTiles[this.x][this.y] = EMPTY_TILE;
//
//            this.x += dx;
//            this.y += dy;
//
//            if(this.x<0) {
//                this.x = 0;
//            }
//            if(this.x>=NUMBER_TILES_HORIZONTAL) {
//                this.x = NUMBER_TILES_HORIZONTAL;
//            }
//
//            if(this.y<0) {
//                this.y = 0;
//            }
//            if(this.y>=NUMBER_TILES_VERTICAL) {
//                this.y = NUMBER_TILES_VERTICAL;
//            }
//
//            mapTiles[this.x][this.y] = PLAYER_TILE+this.id;
//
//            var timer = this.doWalking();
//            var linkToPlayer = this;
//
//            this.getGraphics().animate({
//                left: '+='+(dx*SIZE_TILES_HORIZONTAL),
//                top: '+='+(dy*SIZE_TILES_VERTICAL)
//            }, PLAYER_TIME_TO_CHANGE_TILE, "linear", function(){
//                linkToPlayer.stopWalking(timer);
//            })
//
//
//        }
//    }

    this.setOrientation = function(num) {
        this.orientation = num;
        var fakeOrientation = adaptOrientation(num)
        this.getGraphics().css("background-position", "0px "+(fakeOrientation*(SIZE_TILES_VERTICAL)*(-1))+"px");
    }

    this.doWalking = function() {
        var count = 0;
        var orientation = this.orientation;
        var refObj = this;

        return setInterval(function(){
            var fakeOrientation = adaptOrientation(orientation);
            refObj.getGraphics().css("background-position", (count*(SIZE_TILES_HORIZONTAL)*(-1))+"px "+(fakeOrientation*(SIZE_TILES_VERTICAL)*(-1))+"px");

            count = (++count)%4;
        }, PLAYER_TIME_TO_CHANGE_TILE/4)
    }

    this.stopWalking = function(walkingTimer) {
        clearInterval(walkingTimer);

        var fakeOrientation = adaptOrientation(this.orientation)
        this.getGraphics().css("background-position", "0px "+(fakeOrientation*(SIZE_TILES_VERTICAL)*(-1))+"px");
    }

    console.log("player <"+name+"> is configured");
}

function NonPlayablePlayer(id) {

    this.moving = false;
    this.timerMoving = null;

    // @override
    this.register = function() {

        if(id==undefined) {
            this.id = getNextPlayerId();
        }
        else {
            this.id = id;
        }

        mapOfPlayers[this.id] = this;
    }

    this.doRandomMove = function() {

        if(this.moving == false) {

            this.moving = true;
            var refObj = this;
            timerMoving = setInterval(function() {
                var randomNumber = Math.floor(Math.random()*100);

                // 0.25% = probability of moving
                if(randomNumber>75) {
                    var direction = Math.floor(Math.random()*4);
                    refObj.singleMove(direction);
                }
            }, PLAYER_TIME_TO_CHANGE_TILE*8);
        }
    }

    this.stopRandomMove = function() {

        this.moving = false;

        if(this.timerMoving) {

            clearInterval(this.timerMoving);
            this.timerMoving = null;
        }
    }
}

var playerKeyMovingTimer = null;

function PlayablePlayer(name,id) {

    this.name = name;

    this.moving = false;
    this.timerMoving = null;

    this.controllSettings = []
    this.controllSettings[40] = 0;
    this.controllSettings[37] = 1;
    this.controllSettings[38] = 2;
    this.controllSettings[39] = 3;

    // @override
    this.register = function() {

        if(id==undefined) {
            this.id = getNextPlayerId();
        }
        else {
            this.id = id;
        }

        mapOfPlayers[this.id] = this;

        var refObj = this;
        var makeALastMove = false;

        $('body').keydown(function (event) {

            var direction = -1;

            if(refObj.controllSettings[event.keyCode] != undefined) {
                direction = refObj.controllSettings[event.keyCode];
            }

            if(playerKeyMovingTimer ==  null) {
                makeALastMove = true;
                playerKeyMovingTimer = setInterval(function() {
                    refObj.singleMove(direction);
                    makeALastMove = false;
                }, (PLAYER_TIME_TO_CHANGE_TILE));

            }

        });

        $('body').keyup(function (event) {

            var direction = -1;

            if(refObj.controllSettings[event.keyCode] != undefined) {
                direction = refObj.controllSettings[event.keyCode];
            }

            if(makeALastMove) {
                refObj.singleMove(direction);
            }

            switch (event.keyCode) {
                case 40:
                case 37:
                case 38:
                case 39:

                    if(playerKeyMovingTimer != null) {
                        clearInterval(playerKeyMovingTimer);
                        playerKeyMovingTimer = null;
                    }

                    break;
            }
        });
    }
}

function Grotte() {

    this.orientation = 0;

    this.x = -1;
    this.y = -1;

    this.getGraphics = function() {
        return $("#grotte")
    }

    this.setPosition = function(x,y) {

        this.x = x;
        this.y = y;
    }

    this.display = function() {
        var params = [{id:this.id}]
        $( "#grotteTemplate" ).tmpl(params).appendTo("#scene");
        this.getGraphics().css("background", "url('assets/img/Tiles/grotte_complete.png') 0 0 no-repeat");

        this.getGraphics().css("left",  this.x*SIZE_TILES_HORIZONTAL+"px");
        this.getGraphics().css("top",   this.y*SIZE_TILES_VERTICAL+"px");

        console.log("grotte is displayed");
    }

    console.log("grotte is configured");
}

function addClassCharacter(name, obj) {

    if(indexCharacterClass.indexOf(name) == -1) {

        indexCharacterClass.push(name);
        mapCharacterClass[name] = obj;
    }
}

function initGame(id,startX,startY) {

    // configure the array of tiles
    mapTiles = [];

    indexCharacterClass = [];
    mapCharacterClass = [];

    for(var i=0; i<NUMBER_TILES_HORIZONTAL; i++) {
        var arrayLine = [];
        for( var j=0; j<NUMBER_TILES_VERTICAL; j++) {
            arrayLine.push(EMPTY_TILE);
        }
        mapTiles.push(arrayLine)
    }

    // configure the character classes
    addClassCharacter("player", new CharacterClass("player", "assets/img/Characters/013-Warrior01.png"));
    addClassCharacter("pnj", new CharacterClass("pnj", "assets/img/Characters/067-Goblin01.png"));

    jQuery.extend(PlayablePlayer.prototype,     new Player("player", "player"));
    jQuery.extend(NonPlayablePlayer.prototype,  new Player("pnj", "pnj"));

    // configure the player

    if(player!=null) {
        mapOfPlayers[player.id] = null
        player.id = id;
    }
    else {
        player = new PlayablePlayer("Jonathan",id);
    }

    player.register();
    player.setPosition(startX,startY);
    player.display();

    player.singleMove(DOWN);
    setTimeout(function(){
        player.singleMove(RIGHT);
    },(PLAYER_TIME_TO_CHANGE_TILE+10));
    setTimeout(function(){
        player.singleMove(RIGHT);
    },(PLAYER_TIME_TO_CHANGE_TILE+10)*2);
    setTimeout(function(){
        player.singleMove(RIGHT);
    },(PLAYER_TIME_TO_CHANGE_TILE+10)*3);
    setTimeout(function(){
        player.singleMove(RIGHT);
    },(PLAYER_TIME_TO_CHANGE_TILE+10)*4);
    player.setOrientation(0);

    // configure a pnj

    setTimeout(function(){

//        for(var i=15; i<29;i++) {
//            for(var j=10; j<14; j++) {
//                var pnj = new NonPlayablePlayer();
//                pnj.register();
//                pnj.setPosition(i,j);
//                pnj.display();
//                pnj.doRandomMove();
//            }
//        }

//        for(var i=7; i<29;i=i+3) {
//            for(var j=8; j<14; j=j+3) {
//                var pnj = new NonPlayablePlayer();
//                pnj.register();
//                pnj.setPosition(i,j);
//                pnj.display();
//                pnj.doRandomMove();
//            }
//        }

    }, 2000);

    if(DEBUG) {
        setInterval(function(){
            $("#debug")[0].innerHTML = "";
            for(var i=0; i<30;i++) {
                for(var j=0; j<15; j++) {
                    if(mapTiles[i][j] != EMPTY_TILE) {

                        $("#debug")[0].innerHTML += "<div class=\"debug_square\" style=\"width:32px; height: 48px; z-index:3; position: absolute; background-color: red; opacity: 0.25; top:"+j*SIZE_TILES_VERTICAL+"px; left:"+i*SIZE_TILES_HORIZONTAL+"px;\"></div>";
                    }
                }
            }
        }, 500);
    }
}