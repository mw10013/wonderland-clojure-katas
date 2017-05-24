(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round")
  (testing "queens are higher rank than jacks"
    (is (= :player1 (play-round [:queen :spade] [:jack :heart]))))
  (testing "kings are higher rank than queens"
    (is (= :player2 (play-round [:queen :heart] [:king :club]))))
  (testing "aces are higher rank than kings"
    (is (= :player1 (play-round [:ace :diamond] [:king :heart]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= :player1 (play-round [:ace :club] [:ace :spade]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= :player1 (play-round [:ace :diamond] [:ace :club]))))
  (testing "if the ranks are equal, hearts beat diamonds")
  (is (= :player1 (play-round [:ace :heart] [:ace :diamond]))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= :player2 (play-game [[6 :spade] [5 :spade]] [[7 :spade]])))))

