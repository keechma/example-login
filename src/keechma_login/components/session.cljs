(ns keechma-login.components.session
  (:require [keechma.ui-component :as ui]))

(defn render-login
  "Renders the login screen"
  [ctx]
  [:button.btn.btn-primary
    {:on-click #(ui/send-command ctx :login)}
    "Login to your Trello account"])

(defn render-main-app
  "Renders the main component of the `:main` application"
  [main-app]
  [(:main-component main-app)])

(defn render
  "This component is rendered by the `session` application.
  - If the `session` app's `:main-component` is `nil` it will render the login screen
  - If the `session` app has `:main-component` set render the main component of the 
  `:main` app "
  [ctx]
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
