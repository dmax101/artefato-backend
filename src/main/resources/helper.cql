MATCH (n) DETACH DELETE n;

CREATE (Danilo:User {
    id: '52eeb5f4-43a6-4306-afb0-cb588e0dcd79',
    name: 'danilo',
    email: 'danriba@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: true,
    wallet: '5000.00'
})
CREATE (Sofia:User {
    id: 'c6517cfa-858b-4880-831a-2a3837eb5048',
    name: 'sofia',
    email: 'sofia@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: true,
    wallet: '5000.00'
})
CREATE (Flavia:User {
    id: 'b859a7b5-0ddf-4558-8e36-d5c8bfa1de2d',
    name: 'flavia',
    email: 'flavia@gmail.com',
    bio: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    isCrafter: false,
    wallet: '5000.00'
})
CREATE (Sword:Product {
    id: 'daa5d119-b48f-41cf-bf49-e2835d883610',
    name: 'sword',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '120.00',
    isAvailable: false,
    imageURL: ['https://images.unsplash.com/photo-1542403764-19e1bb75cc75?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80']
})
CREATE (Shield:Product {
    id: 'eb6d93b6-bc00-4200-b725-ecc249634877',
    name: 'shield',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '300.00',
    isAvailable: true,
    imageURL: ['https://static.turbosquid.com/Preview/2014/07/07__04_25_09/spartanshield-0005.jpg58ada6cc-0962-4a30-97ce-5b46452e4a65Zoom.jpg']
})
CREATE (Paint:Product {
    id: '8f0577aa-57a5-4596-9e88-18a76c899214',
    name: 'paint',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    price: '6000.00',
    isAvailable: true,
    imageURL: ['https://www.artmajeur.com/medias/hd/s/o/soulwatercolor/artwork/12799847_girl-sitting-in-cafe-drinking-coffee.jpg']
})
CREATE (Post01:Post {
    id: '230dee80-1f23-4fb9-864f-a802edb477c2',
    title: 'post01',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: false
})
CREATE (Post02:Post {
    id: 'c03e69f2-89ef-49f1-9ed2-8129c1b25dcf',
    title: 'post02',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: false
})
CREATE (Post03:Post {
    id: '7fe0ed5f-2b6f-4d02-85f3-d43b047bf3cf',
    title: 'post03',
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    like: '0',
    share: '0',
    isArchived: true
})

CREATE 
    (Danilo)-[:CRAFTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Sword),
    (Danilo)-[:CRAFTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Shield),
    (Sofia)-[:CRAFTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Paint),
    (Danilo)-[:POSTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post01),
    (Danilo)-[:POSTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post02),
    (Sofia)-[:POSTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Danilo)-[:FOLLOW{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Sofia),
    (Sofia)-[:FOLLOW{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Danilo),
    (Flavia)-[:FOLLOW{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Sofia),
    (Flavia)-[:FOLLOW{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Danilo),
    (Flavia)-[:LIKED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Flavia)-[:LIKED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post02),
    (Sofia)-[:LIKED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post01),
    (Danilo)-[:LIKED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Post03),
    (Post01)-[:ABOUT{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Sword),
    (Post02)-[:ABOUT{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Shield),
    (Post03)-[:ABOUT{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(Paint)

;