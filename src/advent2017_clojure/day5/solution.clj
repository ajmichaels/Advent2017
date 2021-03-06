(ns advent2017-clojure.day5.solution)


(defn- load-input []
  (mapv #(Integer/parseInt %)
        (clojure.string/split-lines
          (slurp "src/advent2017_clojure/day5/input.txt"))))


(defn part1 []
  (println
    (loop [jump-list (load-input)
           pos 0
           steps 0]
      (if (or
            (neg? pos)
            (>= pos (count jump-list)))
        steps
        (recur
          (update
            jump-list
            pos
            inc)
          (+ pos (jump-list pos))
          (inc steps))))))


(defn part2 []
  (println
    ; mutable vector for performance
    (loop [jump-list (transient (load-input))
           pos 0
           steps 0]
      (if (or
            (neg? pos)
            (>= pos (count jump-list)))
        steps
        (let [curr-val (jump-list pos)]
          (recur
            (assoc!
              jump-list
              pos
              (if (>= curr-val 3) (dec curr-val) (inc curr-val)))
            (+ pos curr-val)
            (inc steps)))))))
