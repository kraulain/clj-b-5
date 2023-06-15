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
(def non-pos? (complement pos?))
(non-pos? -23)

(def non-neg? (complement neg?))
(non-neg? 54)

((complement zero?) 9)

;;partial
(def add-three (partial + 3))
(add-three 4)

;;fnil
(def safe-inc (fnil inc 0))
(safe-inc nil)

;;comp
(def person {:name "John Travolta"
             :address {:street "123 mayor street"
                       :postal-code 1234}})

(def postal-code
  (:postal-code (:address person)))

postal-code

(->> person
     :address
     :postal-code)

((comp :postal-code :address) person)

;;juxt
(def person {:first "john"
             :middle "walkins"
             :last "doe"})

(def first-n-last (juxt :first :last))

(first-n-last person)

;;apply
(apply + [1 2])

(apply + [])


;; Recursion exercises
;;length

(defn length*
  "returns the length of a list"
  [ls]
  (if (empty? ls)
    0
    (+ 1 (length* (rest ls)))))

(comment
  (length* nil)
  (length* [])
  (length* (range 10))
)

;;map

(defn map*
  "apply function f to each element in seq ls"
  [f ls]
  (if (empty? ls)
    ()
    (cons (f (first ls))
          (map* f (rest ls)))))

(comment
  (map* inc [])
  (map* inc (range 10))
  (map* str (range 5)))

;; Filter

(defn filter*
  "Return all items in seq ls, that are truthy for predicate p?"
  [p? ls]
  (if (empty? ls)
    ()
    (if (p? (first ls))
      (cons (first ls) (filter* p? (rest ls)))
      (filter* p? (rest ls)))))

(comment
  (filter* even? (range 45))
  (filter* nil? (range 10))
  (filter* (complement nil?) (range 10))
  )

;; Tail recursion
;;filter

(defn filter*-helper
  "Helper function to do filter with tail recursion"
  [p? ls acc]
  (if (empty? ls)
    acc
    (if (p? (first ls))
      (recur p? (rest ls) (conj acc (first ls)))
      (recur p? (rest ls) acc))))

(defn filter*
  "return all items that are tru for predicate p?"
  [p? ls]
  (filter*-helper p? ls []))

(comment
  (filter* even? (range 10))
  )

;; map

(defn map*-helper
  "three argument helper fuction for map*"
  [f ls acc]
  (if (empty? ls)
    acc
    (recur f (rest ls) (conj acc (f (first ls))))))

(defn map*
  "map function f on all items in sequence ls"
  [f ls]
  (map*-helper f ls []))

(comment
  (map* inc (range 5))
  )

;; length

(defn length*-helper
  "helper function for calculating the length of a sequence"
  [ls acc]
  (if (empty? ls)
    acc
    (recur (rest ls) (inc acc))))

(defn length*
  "returns the length of a sequence"
  [ls]
  (length*-helper ls 0))

(comment
  (length* #{1 2 3 4 5})
  )

;; Use loop-recur

;; map

(defn map* [f ls]
  (loop [ls ls acc []]
    (if (empty? ls)
      acc
      (recur (rest ls) (conj acc (f (first ls)))))))

(map* inc (range 10))
