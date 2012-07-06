class Connector
  init: () ->
    if (!("WebSocket" in window))
      console.error("Websockets NOT supported")
      return
