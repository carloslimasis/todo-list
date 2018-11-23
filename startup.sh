#!bin/bash

# 100req/s 10s
vegeta attack -rate=100 -duration=10s -targets=./targets.txt > ./vegeta/results.txt

# output text file
vegeta report -inputs=./vegeta/results.txt -reporter=text

# plot
vegeta report -inputs=./vegeta/results.txt -reporter=plot > ./vegeta/plot.html