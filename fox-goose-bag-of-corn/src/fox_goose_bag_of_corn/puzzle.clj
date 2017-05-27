(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn invalid? [plan [l _ r]]
  (let [unsafe? (fn [x] (some (partial = x) [#{:fox :goose} #{:goose :corn}]))
        cycle? #(some (fn [[[x]]] (-> x set (= l))) (partition 2 (butlast plan)))]
    (or (some unsafe? [l r]) (cycle?))))

(defn advance-plan [plan]
  (let [src-pos (last plan)
        [with-you-idx without-you-idx] (if (some #{:you} (first src-pos))
                                         [0 2] [2 0])
        movers (map #(if (= % :you) #{:you} #{:you %}) (get src-pos with-you-idx))
        move (fn [move-set]
               (let [trans-pos (-> src-pos
                                 (update with-you-idx #(vec (remove move-set %)))
                                 (update 1 #(apply conj % move-set)))
                     dst-pos (-> trans-pos
                               (assoc 1 [:boat])
                               (update without-you-idx #(apply conj % move-set)))]
                 (conj plan trans-pos dst-pos)))]
    (map move movers)))
        
(defn walk [plans]
  (when-first [plan plans]
    (let [pos (map set (last plan))]
      (cond
        (= [#{} #{:boat} #{:you :fox :goose :corn}] pos) plan
        (invalid? plan pos) (recur (rest plans))
        :else (recur (concat (advance-plan plan) (rest plans)))))))

(defn river-crossing-plan []
  (walk [start-pos]))

; (walk [start-pos])
; (invalid? nil [#{:fox :goose} #{:boat} #{:you :corn}])
; (invalid? nil [#{:fox :you} #{:boat} #{:goose :corn}])
; (invalid? nil [#{:fox :goose :you} #{:boat} #{:corn}])
; (invalid? nil [#{:fox :goose :you} #{:boat} #{:corn}])
; (advance-plan [[[:fox :goose :corn :you] [:boat] []]])
; (advance-plan [[[:fox :corn] [:boat] [:goose :you]]])

(comment
  (invalid?
    [[[:fox :goose :corn :you] [:boat] []]
     [[:fox :corn] [:you :boat :goose] []]
     [[:fox :corn] [:boat] [:you :goose]]
     [[:fox :corn] [:you :boat] [:goose]]
     [[:you :fox :corn] [:boat] [:goose]]]
    [#{:you :fox :corn} #{:boat} #{:goose}])
  (invalid?
    [[[:fox :goose :corn :you] [:boat] []]
     [[:fox :corn] [:you :boat :goose] []]
     [[:you :fox :corn] [:boat] [:goose]]
     [[:fox :corn] [:you :boat] [:goose]]
     [[:you :fox :corn] [:boat] [:goose]]]
    [#{:you :fox :corn} #{:boat} #{:goose}])
)
