(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(def prec (into {} (map vector (concat ranks suits) (range))))

(defn play-round [player1-card player2-card]
  (let [player1-rank-prec (prec (first player1-card))
        player2-rank-prec (prec (first player2-card))]
    (cond
      (> player1-rank-prec player2-rank-prec) :player1
      (< player1-rank-prec player2-rank-prec) :player2
      :else (let [player1-suit-prec (prec (second player1-card))
                  player2-suit-prec (prec (second player2-card))]
              (if (> player1-suit-prec player2-suit-prec)
                :player1
                :player2)))))

; (play-round [:jack :spade] [:jack :heart])
; (play-round [:jack :heart] [:jack :spade])
; (play-round [:queen :heart] [:jack :spade])

(defn play-game [player1-cards player2-cards]
  (let [c1 (first player1-cards)
        c2 (first player2-cards)]
    (cond
      (= nil c1 c2) :draw
      (nil? c1) :player2
      (nil? c2) :player1
      :else (if (= :player1 (play-round c1 c2))
              (recur (conj player1-cards c1 c2) (subvec player2-cards 1))
              (recur (subvec player1-cards 1) (conj player2-cards c1 c2))))))

; (play-game [] [])
; (play-game nil nil)
; (play-game [[2 :spade]] [[3 :spade]])
; (play-game [[6 :spade] [5 :spade]] [[7 :spade]])
; (play-game [[7 :spade]] [[6 :spade] [5 :spade]])

