SELECT P.id, P.price, P.amount, 
CASE
WHEN T.id IS NOT NULL THEN T.height
WHEN F.id IS NOT NULL THEN F.color
WHEN D.id IS NOT NULL THEN D.material
ELSE NULL END AS specific_characteristic
FROM Products AS P
LEFT JOIN Trees T ON P.id = T.id
LEFT JOIN Flowers AS F ON P.id = F.id
LEFT JOIN Decorations AS D ON P.id = D.id;

DELETE FROM products;
Delete FROM Flowers;
delete from trees;
delete from decorations;