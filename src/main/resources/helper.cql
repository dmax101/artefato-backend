CREATE (Danilo:User {
    id: 0,
    name: 'Danilo',
    email: 'Danilo@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: true,
    wallet: '0'
})
CREATE (Sofia:User {
    id: 1,
    name: 'Sofia',
    email: 'Sofia@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: true,
    wallet: '0'
})
CREATE (Flavia:User {
    id: 2,
    name: 'Flavia',
    email: 'Flavia@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: false,
    wallet: '0'
})
CREATE (Sword:Product {
    id: 3,
    name: 'Sword',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '9200',
    isAvailable: false,
    imageURL: ['https://images.unsplash.com/photo-1542403764-19e1bb75cc75?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80']
})
CREATE (Shield:Product {
    id: 4,
    name: 'Shield',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '4500',
    isAvailable: true,
    imageURL: ['https://static.turbosquid.com/Preview/2014/07/07__04_25_09/spartanshield-0005.jpg58ada6cc-0962-4a30-97ce-5b46452e4a65Zoom.jpg']
})
CREATE (Paint:Product {
    id: 5,
    name: 'Paint',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '21000',
    isAvailable: true,
    imageURL: ['https://www.artmajeur.com/medias/hd/s/o/soulwatercolor/artwork/12799847_girl-sitting-in-cafe-drinking-coffee.jpg']
})
CREATE (Post01:Post {
    id: 6,
    title: 'Post01',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: false
})
CREATE (Post02:Post {
    id: 7,
    title: 'Post02',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: false
})
CREATE (Post03:Post {
    id: 8,
    title: 'Post03',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: true
})

CREATE 
    (Danilo)-[:CRAFTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Sword),
    (Danilo)-[:CRAFTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Shield),
    (Sofia)-[:CRAFTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Paint),
    (Danilo)-[:POSTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post01),
    (Danilo)-[:POSTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post02),
    (Sofia)-[:POSTED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Danilo)-[:FOLLOW{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Sofia),
    (Sofia)-[:FOLLOW{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Danilo),
    (Flavia)-[:FOLLOW{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Sofia),
    (Flavia)-[:FOLLOW{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Danilo),
    (Flavia)-[:LIKED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Flavia)-[:LIKED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post02),
    (Sofia)-[:LIKED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post01),
    (Danilo)-[:LIKED{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Post01)-[:ABOUT{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Sword),
    (Post02)-[:ABOUT{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Shield),
    (Post03)-[:ABOUT{creation: datetime({timezone: 'America/Sao Paulo'})}]->(Paint)

;

MATCH (n) DETACH DELETE n;