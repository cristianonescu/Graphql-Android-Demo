# ANDROID GRAPHQL SETUP - using graphcool and apollo


## Usage
GET NPM
in terminal run: npm install -g graphcool
in terminal run: npm install -g apollo-codegen
run in terminal: apollo-codegen introspect-schema https://urlsche.ma/blablabla —output schema.json
paste it into the project over the current schema.json
   6 replace files deletePost.graphql, getAllPosts.graphql, newPosts.graphql with your own mutations ( test them in the playground. also check this documentation: https://www.graph.cool/docs/reference/graphql-api/mutation-api-ol0yuoz6go#overview       )
——————— if new mutations were added, rebuild project in order to generate the sourcefiles
   7. In MyApolloClient replace the BASE_URL with your graphql link (Check graphcool->endpoints)
