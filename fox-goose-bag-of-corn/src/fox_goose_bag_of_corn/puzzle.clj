(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]
                [[:fox :corn] [:you :boat :goose] []]
                [[:fox :corn] [:boat] [:you :goose]]
                [[:fox :corn] [:you :boat] [:goose]]
                [[:you :fox :corn] [:boat] [:goose]]
                [[:fox] [:you :corn :boat] [:goose]]
                [[:fox] [:boat] [:you :goose :corn]]
                [[:fox] [:you :goose :boat] [:corn]]
                [[:you :goose :fox] [:boat] [:corn]]
                [[:goose] [:you :fox :boat] [:corn]]
                [[:goose] [:boat] [:corn :you :fox]]
                [[:goose] [:you :boat] [:corn :fox]]
                [[:goose :you] [:boat] [:corn :fox]]
                [[] [:goose :you :boat] [:corn :fox]]
                [[] [:boat][:fox :goose :corn :you]]])

(defn river-crossing-plan []
  start-pos)
