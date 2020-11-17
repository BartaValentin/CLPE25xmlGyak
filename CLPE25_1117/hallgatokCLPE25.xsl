<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:foo="http://www.foo.org/" xmlns:bar="http://www.bar.org">
<xsl:template match="/">
  <html>
  <body>
  <h2>My CD Collection</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>keresztnev</th>
        <th>vezeteknev</th>
        <th>becenev</th>
        <th>fizetes</th>
      </tr>
      <xsl:for-each select="catalog/foo:cd">
      <tr>
        <td><xsl:value-of select="keresztnev"/></td>
        <td><xsl:value-of select="vezeteknev"/></td>
        <td><xsl:value-of select="becenev"/></td>
        <td><xsl:value-of select="fizetes"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>