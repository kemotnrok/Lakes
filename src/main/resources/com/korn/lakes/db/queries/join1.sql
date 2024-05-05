SELECT lakeName, countryName 
FROM lakes l
INNER JOIN lake_country_relation lcr ON l.id = lcr.lakeId
INNER JOIN countries c ON c.id = lcr.countryId;