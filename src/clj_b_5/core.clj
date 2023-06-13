(ns clj-b-5.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; Map

;;increment all numbers in collection
(map inc [1 3 5 7 9])

;; Filter

;;filter all even numbers from collection
(filter even? (range 10))

;;filter records using a keyword as predicate
(filter :a [{:a 1} {:a 2} {:b 3} {:a false}])

;; custom predicate function
(filter (fn [x]
          (not (zero? (mod x 3))))
        (range 10))

;;identity
(identity 10)

(identity 200)

(identity :a-keyword)

;;constantly
(def my-func (constantly 5))
(my-func)

((constantly :hello))

;;complement

;;partial




;;fnil

;;comp

;;juxt

;;apply
