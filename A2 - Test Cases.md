<table>
<colgroup>
<col style="width: 88%" />
<col style="width: 11%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Testcase</strong></th>
<th><blockquote>
<p><strong>Pass/Fail</strong></p>
</blockquote></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Testcase 2001</strong></p>
<p>Server startup check with default arguments Instructions:</p>
<ol type="1">
<li><p>Start the server program Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The server reports that it is listening for clients by displaying the
following message:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>Server listening for clients on port 5555</em></p>
</blockquote>
<ol start="2" type="1">
<li><p>The server console waits for user input. Cleanup:</p></li>
</ol>
<p>Terminate the server program.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2002</strong></p>
<p>Client startup check without a login Instructions:</p>
<ol type="1">
<li><p>Start the Client program without specifying the loginID as an
argument. Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The client reports it cannot connect without a login by
displaying:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>ERROR - No login ID specified. Connection aborted.</em></p>
</blockquote>
<ol start="2" type="1">
<li><p>The client terminates. Cleanup: (if client is still active)
Terminate the client program.</p></li>
</ol></td>
<td>Pass</td>
</tr>
<tr class="odd">
<td><p><strong>Testcase 2003</strong></p>
<p>Client startup check with a login and without a server
Instructions:</p>
<ol type="1">
<li><p>Start the Client program while specifying loginID as an argument.
Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The client reports it cannot connect to a server by displaying:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>ERROR - Can't setup connection! Terminating client.</em></p>
</blockquote>
<ol start="2" type="1">
<li><p>The client terminates. Cleanup: (if client is still active)
Terminate the client program.</p></li>
</ol></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2004</strong></p>
<p>Client connection with default arguments Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server (Testcase 2001, instruction 1)</p>
</blockquote></li>
<li><p>Start a client (Testcase 2003, instruction 1) Expected
results:</p>
<ol type="1">
<li><blockquote>
<p>The server displays the following messages in sequence:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>A new client has connected to the server. Message received:
#login &lt;loginID&gt; from null.</em></p>
<p><em>&lt;loginID&gt; has logged on.</em></p>
<p><strong>Note:</strong> the server specifies that it received a
message from null as this is the first message received from this
client. It will record the loginID of this client for later messages.
Hence, for later messages, it should display:</p>
<p><em>Message received: &lt;user input&gt; from
&lt;loginID&gt;</em></p>
</blockquote></td>
<td>Pass</td>
</tr>
</tbody>
</table>

<table>
<colgroup>
<col style="width: 88%" />
<col style="width: 11%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Testcase</strong></th>
<th><blockquote>
<p><strong>Pass/Fail</strong></p>
</blockquote></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><blockquote>
<p>Where &lt;user input&gt; is the content of the message received and
&lt;loginID&gt; is the loginID of the sending client.</p>
</blockquote>
<ol start="2" type="1">
<li><blockquote>
<p>The client displays message:</p>
</blockquote></li>
</ol>
<blockquote>
<p><em>&lt;loginID&gt; has logged on.</em></p>
</blockquote>
<ol start="3" type="1">
<li><p>The client and the server wait for user input. Cleanup: (unless
proceeding to Testcase 2005) Terminate the client program.</p></li>
</ol>
<p>Terminate the server program.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2005</strong></p>
<p>Client Data transfer and data echo Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server and a client using default arguments (Testcase 2004
instructions).</p>
</blockquote></li>
<li><p>Once connected, type in data on the client console and press
ENTER. Expected results:</p>
<ol type="1">
<li><blockquote>
<p>The message is echoed on the client side, but is preceded by the
sender's loginID and the greater than symbol ("&gt;").</p>
</blockquote></li>
<li><blockquote>
<p>The server displays a message similar to the following:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>Message received: &lt;user input&gt; from
&lt;loginID&gt;</em></p>
</blockquote>
<p>Cleanup:</p>
<p>Terminate the client program. Terminate the server program.</p></td>
<td>Pass</td>
</tr>
<tr class="odd">
<td><p><strong>Testcase 2006</strong></p>
<p>Multiple local connections Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server and multiple clients with DIFFERENT loginIDs and
connect them to the server using default arguments. (Testcase 2005
instructions).</p>
</blockquote></li>
<li><blockquote>
<p>Start typing on all the client consoles AND the server console,
pressing ENTER to send each message.</p>
</blockquote></li>
</ol>
<p>Expected results:</p>
<ol type="1">
<li><blockquote>
<p>All client messages are echoed as in Testcase 2005.</p>
</blockquote></li>
<li><blockquote>
<p>All messages from the server console are echoed on the server console
and to all clients, but are preceded by "SERVER MESSAGE&gt; ".</p>
</blockquote></li>
</ol>
<p>Cleanup:</p>
<p>Terminate the clients. Terminate the server program.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2007</strong></p>
<p>Server termination command check Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server (Testcase 2001 instruction 1) using default
arguments.</p>
</blockquote></li>
<li><p>Type "#quit" into the server's console. Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The server quits.</p>
</blockquote></li>
</ol></li>
</ol>
<p>Cleanup (If the server is still active): Terminate the server
program.</p></td>
<td>Pass</td>
</tr>
</tbody>
</table>

<table>
<colgroup>
<col style="width: 88%" />
<col style="width: 11%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Testcase</strong></th>
<th><blockquote>
<p><strong>Pass/Fail</strong></p>
</blockquote></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p><strong>Testcase 2008</strong></p>
<p>Server close command check Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server and connect a client to it. (Testcase 2004)</p>
</blockquote></li>
<li><blockquote>
<p>Stop the server using the #stop command.</p>
</blockquote></li>
<li><p>Type "#close" into the server's console. Expected result:</p>
<ol type="1">
<li><blockquote>
<p>Server displays in sequence:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>Server has stopped listening for connections.</em></p>
<p><em>&lt;loginID&gt; has disconnected.</em></p>
</blockquote>
<ol start="2" type="1">
<li><blockquote>
<p>The client displays:</p>
</blockquote></li>
</ol>
<blockquote>
<p><em>The server has shut down.</em></p>
</blockquote>
<ol start="3" type="1">
<li><p>The client terminates Cleanup:</p></li>
</ol>
<p>Terminate the client program. Terminate the server program.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2009</strong> Server restart Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server.</p>
</blockquote></li>
<li><blockquote>
<p>Close the server using the #close command.</p>
</blockquote></li>
<li><blockquote>
<p>Type "#start" into the server's console.</p>
</blockquote></li>
<li><p>Attempt to connect a client. Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The server closes, restarts and then displays:</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>Server listening for connections on port 5555.</em></p>
</blockquote>
<ol start="2" type="1">
<li><p>The client connects normally as described in Testcase 2004.
Cleanup:</p></li>
</ol>
<p>Terminate the client program.</p>
<p>Type #quit to kill the server.</p></td>
<td>Pass</td>
</tr>
<tr class="odd">
<td><p><strong>Testcase 2010</strong></p>
<p>Client termination command check Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server</p>
</blockquote></li>
<li><blockquote>
<p>Connect a client.</p>
</blockquote></li>
<li><p>Type "#quit" into the client's console. Expected result:</p>
<ol type="1">
<li><p>Client terminates. Cleanup: (If client is still active)</p></li>
</ol></li>
</ol>
<p>Terminate the client program.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2011</strong> Client logoff check
Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server (Testcase 1001, instruction 1), and then connect a
single client to this server.</p>
</blockquote></li>
<li><p>Type "#logoff" into this client's console. Expected
results:</p></li>
</ol></td>
<td>Pass</td>
</tr>
</tbody>
</table>

<table>
<colgroup>
<col style="width: 88%" />
<col style="width: 11%" />
</colgroup>
<thead>
<tr class="header">
<th><strong>Testcase</strong></th>
<th><blockquote>
<p><strong>Pass/Fail</strong></p>
</blockquote></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><ol type="1">
<li><p>Client disconnects and displays Connection closed.
Cleanup:</p></li>
</ol>
<p>Type "#quit" to kill the client.</p></td>
<td>Pass</td>
</tr>
<tr class="even">
<td><p><strong>Testcase 2012</strong></p>
<p>Starting a server on a non-default port Instructions:</p>
<ol type="1">
<li><p>Start a server while specifying port <strong>1234</strong> as an
argument. Expected result:</p>
<ol type="1">
<li><blockquote>
<p>The server displays</p>
</blockquote></li>
</ol></li>
</ol>
<blockquote>
<p><em>Server listening for connections on port 1234.</em></p>
</blockquote>
<p>Cleanup:</p>
<p>Type #quit to kill the server.</p></td>
<td>Pass</td>
</tr>
<tr class="odd">
<td><p><strong>Testcase 2013</strong></p>
<p>Connecting a client to a non-default port Instructions:</p>
<ol type="1">
<li><blockquote>
<p>Start a server on port 1234</p>
</blockquote></li>
<li><blockquote>
<p>Start a client with the arguments: <strong>&lt;loginID&gt;
&lt;host&gt; 1234</strong></p>
</blockquote></li>
</ol>
<blockquote>
<p>(replace the parameters by appropriate values).</p>
</blockquote>
<p>Expected Result:</p>
<ol type="1">
<li><blockquote>
<p>The connection occurs normally.</p>
</blockquote></li>
</ol></td>
<td>Pass</td>
</tr>
</tbody>
</table>
