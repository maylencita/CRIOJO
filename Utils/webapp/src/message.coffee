class Message
    constructor: (@text) ->

    toString: () ->
        "a generated string"

message = new Message "a message"
