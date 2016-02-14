(ns keechma-login.components.session
  (:require [keechma.ui-component :as ui]))

(defn render-login [ctx]
  [:button.btn.btn-primary
    {:on-click #(ui/send-command ctx :login)}
    "Login to your Trello account"])

(defn render-main-app [main-app]
  [(:main-component main-app)])

(defn render [ctx]
  (fn []
    (let [main-app-sub (ui/subscription ctx :main-app)
          main-app @main-app-sub]
      [:div.container>div.row>div.col-xs-12>div.jumbotron
       (if main-app
         (render-main-app main-app)
         (render-login ctx))])))

(def component (ui/constructor
                {:renderer render
                 :subscription-deps [:main-app]
                 :topic :login}))
