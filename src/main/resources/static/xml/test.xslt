<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Продукти для тварин</title>
            </head>
            <body>
                <h1>Каталог продуктів</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Назва</th>
                        <th>Опис</th>
                        <th>Ціна</th>
                        <th>Категорія</th>
                        <th>В наявності</th>
                    </tr>
                    <xsl:for-each select="Products/Product">
                        <tr>
                            <td><xsl:value-of select="ID"/></td>
                            <td><xsl:value-of select="Name"/></td>
                            <td><xsl:value-of select="Description"/></td>
                            <td><xsl:value-of select="Price"/></td>
                            <td><xsl:value-of select="Category"/></td>
                            <td><xsl:value-of select="CountInStock"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>