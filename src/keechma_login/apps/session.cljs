(ns keechma-login.apps.session
  (:require [keechma-login.controllers.login :as login]
            [keechma-login.controllers.logout :as logout]
            [keechma-login.components.session :as session-c]
            [keechma-login.subscriptions :as subs]
            [keechma-login.apps.main :as main]))


(def app {:routes ["session/:session-action"]
          :controllers {:login (login/->Controller main/app)
                        :logout (logout/->Controller)}
          :components {:main session-c/component}
          :subscriptions {:main-app subs/main-app}
          :html-element (.getElementById js/document "app")})
