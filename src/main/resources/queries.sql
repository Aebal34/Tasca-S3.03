select products.*, trees.height FROM 
products, trees
WHERE products.id = trees.id;