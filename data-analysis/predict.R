# usage:
# Rscript predict.R bal household education state family/friends credit loans layoff savings
args <- commandArgs(TRUE)
a <- as.double(args[1])
b <- as.double(args[2])
c <- as.double(args[3])
d.tmp <- args[4]
e <- as.double(args[5])
f <- as.double(args[6])
g <- as.double(args[7])
h <- as.double(args[8])
i <- as.double(args[9])


states.ranks <- cbind(read.table('states.txt'), 1:54)
d <- as.vector(unlist(states.ranks[states.ranks$V1==d.tmp,][2]))

load('model.Rda')

test <- rbind(data.frame(), c(a,b,c,d,e,f,g,h,i))
colnames(test) <- names(model$coefficients)[-1]

p <- predict(model, newdata=test, type='response')
out <- p*100
cat(out)
