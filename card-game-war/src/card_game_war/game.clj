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

(defn play-game [player1-cards player2-cards])
