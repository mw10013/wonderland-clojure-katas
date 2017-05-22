(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn neighbors [w]
  (filter
    #(and
       (= (count %) (count w))
       (= (count (remove true? (map = % w))) 1))
    words))

; (neighbors "bank")
; (neighbors "bonk")
; (neighbors "book")
; (neighbors "door")
; (neighbors "look")        
; (neighbors "wheat")

(defn walk [paths dest]
  (if-let [p (first paths)]
    (if (= (last p) dest)
      p
      (when-let [ns (neighbors (last p))]
        (recur (concat
                 (map (partial conj p) (remove (set p) ns))
                 (rest paths))
          dest)))
    []))


(defn doublets [word1 word2]
  (walk [[word1]] word2))

; (doublets "door" "lock")
; (doublets "bank" "loan")
; (doublets "wheat" "bread")
; (doublets "ye" "freezer")
