# ANDROID GRAPHQL SETUP - using graphcool and apollo


## Usage
1. setup NPM (if you don't have it already)
2. install graphcool:
```bash
    $ npm install -g graphcool
```
3. install apollo codegen:
```bash
    $ npm install -g apollo-codegen
```
4. generate your schema:
```bash
    $ apollo-codegen introspect-schema https://urlsche.ma/blablabla —output schema.json
```
5. paste the schema.json over the current schema.json inside project
6. replace files deletePost.graphql, getAllPosts.graphql, newPosts.graphql with your own mutations ( test them in the playground. also check this documentation: https://www.graph.cool/docs/reference/graphql-api/mutation-api-ol0yuoz6go#overview       )
——————— if new mutations were added, rebuild project in order to generate the sourcefiles
7. In MyApolloClient replace the BASE_URL with your graphql link (Check graphcool->endpoints)


# Todo:
-   Dialog to notify user when action is performed
# Features:
-  delete
-  add
- list all