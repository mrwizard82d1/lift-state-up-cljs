(ns lift-state-up.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {}))

(defn boiling-verdict [data owner]
  (reify 
    om/IRender
    (render [_]
      (dom/p nil (if (>= data 100)
                   "The water would boil."
                   "The water would not boil.")))))

(defn calculator [data owner]
  (reify
    om/IInitState
    (init-state [_]
      (println "In `init-state`. app-state=" @app-state)
      {:temperature ""})
    om/IRender
    (render [_]
      (println "In `render`. app-state=" @app-state)
      (dom/fieldset nil
               (dom/legend nil "Enter the temperature in Celsius:")
               (om/build boiling-verdict (:temperature data))))))

(om/root calculator 
         app-state
         {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
