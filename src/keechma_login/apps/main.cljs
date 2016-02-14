(ns keechma-login.apps.main
  (:require [keechma-login.controllers.trello :as trello]
            [keechma-login.components.trello :as trello-c]
            [keechma-login.subscriptions :refer [current-user]]))


(def app {:routes ["session/:session-action"]
          :controllers {:trello (trello/->Controller)}
          :components {:main trello-c/component}
          :subscriptions {:current-user current-user}})
