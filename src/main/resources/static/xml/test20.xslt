<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/StoreData">
        <html>
            <head>
                <title>Store Data</title>
                <style>
                    body { font-family: Arial, sans-serif; }
                    h2 { color: #4CAF50; }
                    table { width: 100%; border-collapse: collapse; margin: 20px 0; }
                    th, td { border: 1px solid #ddd; padding: 8px; }
                    th { background-color: #4CAF50; color: white; }
                </style>
            </head>
            <body>
                <h1>Store Data</h1>

                <!-- Products Section -->
                <h2>Products</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Count In Stock</th>
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

                <!-- Carts Section -->
                <h2>Carts</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Product IDs</th>
                        <th>Total Amount</th>
                    </tr>
                    <xsl:for-each select="Carts/Cart">
                        <tr>
                            <td><xsl:value-of select="ID"/></td>
                            <td><xsl:value-of select="UserID"/></td>
                            <td>
                                <xsl:for-each select="Products/ProductID">
                                    <xsl:value-of select="."/>
                                    <xsl:if test="position() != last()">, </xsl:if>
                                </xsl:for-each>
                            </td>
                            <td><xsl:value-of select="TotalAmount"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

                <!-- Users Section -->
                <h2>Users</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Login</th>
                        <th>Name</th>
                    </tr>
                    <xsl:for-each select="Users/User">
                        <tr>
                            <td><xsl:value-of select="ID"/></td>
                            <td><xsl:value-of select="Type"/></td>
                            <td><xsl:value-of select="Login"/></td>
                            <td><xsl:value-of select="Name"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>