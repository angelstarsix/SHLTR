mydata <- read.table('simulated_2.tsv', header=T)

model <- glm(homeless.status ~ ., data = mydata, family = "binomial")
