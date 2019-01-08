//package java.net;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InvalidObjectException;
//import java.io.ObjectStreamException;
//import java.io.ObjectStreamField;
//import java.io.ObjectInputStream.GetField;
//import java.util.Hashtable;
//import java.util.StringTokenizer;
//import sun.security.util.SecurityConstants;
//
///**
// * Class {@code URL} represents a Uniform Resource
// * Locator, a pointer to a "resource" on the World
// * Wide Web. A resource can be something as simple as a file or a
// * directory, or it can be a reference to a more complicated object,
// * such as a query to a database or to a search engine. More
// * information on the types of URLs and their formats can be found at:
// * <a href=
// * "http://web.archive.org/web/20051219043731/http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html">
// * <i>Types of URL</i></a>
// * <p>
// * In general, a URL can be broken into several parts. Consider the
// * following example:
// * <blockquote><pre>
// *     http://www.example.com/docs/resource1.html
// * </pre></blockquote>
// * <p>
// * The URL above indicates that the protocol to use is
// * {@code http} (HyperText Transfer Protocol) and that the
// * information resides on a host machine named
// * {@code www.example.com}. The information on that host
// * machine is named {@code /docs/resource1.html}. The exact
// * meaning of this name on the host machine is both protocol
// * dependent and host dependent. The information normally resides in
// * a file, but it could be generated on the fly. This component of
// * the URL is called the <i>path</i> component.
// * <p>
// * A URL can optionally specify a "port", which is the
// * port number to which the TCP connection is made on the remote host
// * machine. If the port is not specified, the default port for
// * the protocol is used instead. For example, the default port for
// * {@code http} is {@code 80}. An alternative port could be
// * specified as:
// * <blockquote><pre>
// *     http://www.example.com:1080/docs/resource1.html
// * </pre></blockquote>
// * <p>
// * The syntax of {@code URL} is defined by  <a
// * href="http://www.ietf.org/rfc/rfc2396.txt"><i>RFC&nbsp;2396: Uniform
// * Resource Identifiers (URI): Generic Syntax</i></a>, amended by <a
// * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
// * Literal IPv6 Addresses in URLs</i></a>. The Literal IPv6 address format
// * also supports scope_ids. The syntax and usage of scope_ids is described
// * <a href="Inet6Address.html#scoped">here</a>.
// * <p>
// * A URL may have appended to it a "fragment", also known
// * as a "ref" or a "reference". The fragment is indicated by the sharp
// * sign character "#" followed by more characters. For example,
// * <blockquote><pre>
// *     http://java.sun.com/index.html#chapter1
// * </pre></blockquote>
// * <p>
// * This fragment is not technically part of the URL. Rather, it
// * indicates that after the specified resource is retrieved, the
// * application is specifically interested in that part of the
// * document that has the tag {@code chapter1} attached to it. The
// * meaning of a tag is resource specific.
// * <p>
// * An application can also specify a "relative URL",
// * which contains only enough information to reach the resource
// * relative to another URL. Relative URLs are frequently used within
// * HTML pages. For example, if the contents of the URL:
// * <blockquote><pre>
// *     http://java.sun.com/index.html
// * </pre></blockquote>
// * contained within it the relative URL:
// * <blockquote><pre>
// *     FAQ.html
// * </pre></blockquote>
// * it would be a shorthand for:
// * <blockquote><pre>
// *     http://java.sun.com/FAQ.html
// * </pre></blockquote>
// * <p>
// * The relative URL need not specify all the components of a URL. If
// * the protocol, host name, or port number is missing, the value is
// * inherited from the fully specified URL. The file component must be
// * specified. The optional fragment is not inherited.
// * <p>
// * The URL class does not itself encode or decode any URL components
// * according to the escaping mechanism defined in RFC2396. It is the
// * responsibility of the caller to encode any fields, which need to be
// * escaped prior to calling URL, and also to decode any escaped fields,
// * that are returned from URL. Furthermore, because URL has no knowledge
// * of URL escaping, it does not recognise equivalence between the encoded
// * or decoded form of the same URL. For example, the two URLs:<br>
// * <pre>    http://foo.com/hello world/ and http://foo.com/hello%20world</pre>
// * would be considered not equal to each other.
// * <p>
// * Note, the {@link java.net.URI} class does perform escaping of its
// * component fields in certain circumstances. The recommended way
// * to manage the encoding and decoding of URLs is to use {@link java.net.URI},
// * and to convert between these two classes using {@link #toURI()} and
// * {@link URI#toURL()}.
// * <p>
// * The {@link URLEncoder} and {@link URLDecoder} classes can also be
// * used, but only for HTML form encoding, which is not the same
// * as the encoding scheme defined in RFC2396.
// *
// * @author  James Gosling
// * @since JDK1.0
// */
//public final class URL {
//    /**
//     * Creates a {@code URL} object from the specified
//     * {@code protocol}, {@code host}, {@code port}
//     * number, and {@code file}.<p>
//     *
//     * {@code host} can be expressed as a host name or a literal
//     * IP address. If IPv6 literal address is used, it should be
//     * enclosed in square brackets ({@code '['} and {@code ']'}), as
//     * specified by <a
//     * href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>;
//     * However, the literal IPv6 address format defined in <a
//     * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IP
//     * Version 6 Addressing Architecture</i></a> is also accepted.<p>
//     *
//     * Specifying a {@code port} number of {@code -1}
//     * indicates that the URL should use the default port for the
//     * protocol.<p>
//     *
//     * If this is the first URL object being created with the specified
//     * protocol, a <i>stream protocol handler</i> object, an instance of
//     * class {@code URLStreamHandler}, is created for that protocol:
//     * <ol>
//     * <li>If the application has previously set up an instance of
//     *     {@code URLStreamHandlerFactory} as the stream handler factory,
//     *     then the {@code createURLStreamHandler} method of that instance
//     *     is called with the protocol string as an argument to create the
//     *     stream protocol handler.
//     * <li>If no {@code URLStreamHandlerFactory} has yet been set up,
//     *     or if the factory's {@code createURLStreamHandler} method
//     *     returns {@code null}, then the constructor finds the
//     *     value of the system property:
//     *     <blockquote><pre>
//     *         java.protocol.handler.pkgs
//     *     </pre></blockquote>
//     *     If the value of that system property is not {@code null},
//     *     it is interpreted as a list of packages separated by a vertical
//     *     slash character '{@code |}'. The constructor tries to load
//     *     the class named:
//     *     <blockquote><pre>
//     *         &lt;<i>package</i>&gt;.&lt;<i>protocol</i>&gt;.Handler
//     *     </pre></blockquote>
//     *     where &lt;<i>package</i>&gt; is replaced by the name of the package
//     *     and &lt;<i>protocol</i>&gt; is replaced by the name of the protocol.
//     *     If this class does not exist, or if the class exists but it is not
//     *     a subclass of {@code URLStreamHandler}, then the next package
//     *     in the list is tried.
//     * <li>If the previous step fails to find a protocol handler, then the
//     *     constructor tries to load from a system default package.
//     *     <blockquote><pre>
//     *         &lt;<i>system default package</i>&gt;.&lt;<i>protocol</i>&gt;.Handler
//     *     </pre></blockquote>
//     *     If this class does not exist, or if the class exists but it is not a
//     *     subclass of {@code URLStreamHandler}, then a
//     *     {@code MalformedURLException} is thrown.
//     * </ol>
//     *
//     * <p>Protocol handlers for the following protocols are guaranteed
//     * to exist on the search path :-
//     * <blockquote><pre>
//     *     http, https, file, and jar
//     * </pre></blockquote>
//     * Protocol handlers for additional protocols may also be
//     * available.
//     *
//     * <p>No validation of the inputs is performed by this constructor.
//     *
//     * @param      protocol   the name of the protocol to use.
//     * @param      host       the name of the host.
//     * @param      port       the port number on the host.
//     * @param      file       the file on the host
//     * @exception  MalformedURLException  if an unknown protocol is specified.
//     * @see        java.lang.System#getProperty(java.lang.String)
//     * @see        java.net.URL#setURLStreamHandlerFactory(
//     *                  java.net.URLStreamHandlerFactory)
//     * @see        java.net.URLStreamHandler
//     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(
//     *                  java.lang.String)
//     */
//    public URL(String protocol, String host, int port, String file) throws MalformedURLException {
//        this(protocol, host, port, file, null);
//    }
//
//    /**
//     * Creates a URL from the specified {@code protocol}
//     * name, {@code host} name, and {@code file} name. The
//     * default port for the specified protocol is used.
//     * <p>
//     * This method is equivalent to calling the four-argument
//     * constructor with the arguments being {@code protocol},
//     * {@code host}, {@code -1}, and {@code file}.
//     *
//     * No validation of the inputs is performed by this constructor.
//     *
//     * @param      protocol   the name of the protocol to use.
//     * @param      host       the name of the host.
//     * @param      file       the file on the host.
//     * @exception  MalformedURLException  if an unknown protocol is specified.
//     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
//     *                  int, java.lang.String)
//     */
//    public URL(String protocol, String host, String file) throws MalformedURLException {
//        this(protocol, host, -1, file);
//    }
//
//    /**
//     * Creates a {@code URL} object from the specified
//     * {@code protocol}, {@code host}, {@code port}
//     * number, {@code file}, and {@code handler}. Specifying
//     * a {@code port} number of {@code -1} indicates that
//     * the URL should use the default port for the protocol. Specifying
//     * a {@code handler} of {@code null} indicates that the URL
//     * should use a default stream handler for the protocol, as outlined
//     * for:
//     *     java.net.URL#URL(java.lang.String, java.lang.String, int,
//     *                      java.lang.String)
//     *
//     * <p>If the handler is not null and there is a security manager,
//     * the security manager's {@code checkPermission}
//     * method is called with a
//     * {@code NetPermission("specifyStreamHandler")} permission.
//     * This may result in a SecurityException.
//     *
//     * No validation of the inputs is performed by this constructor.
//     *
//     * @param      protocol   the name of the protocol to use.
//     * @param      host       the name of the host.
//     * @param      port       the port number on the host.
//     * @param      file       the file on the host
//     * @param      handler    the stream handler for the URL.
//     * @exception  MalformedURLException  if an unknown protocol is specified.
//     * @exception  SecurityException
//     *        if a security manager exists and its
//     *        {@code checkPermission} method doesn't allow
//     *        specifying a stream handler explicitly.
//     * @see        java.lang.System#getProperty(java.lang.String)
//     * @see        java.net.URL#setURLStreamHandlerFactory(
//     *                  java.net.URLStreamHandlerFactory)
//     * @see        java.net.URLStreamHandler
//     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(
//     *                  java.lang.String)
//     * @see        SecurityManager#checkPermission
//     * @see        java.net.NetPermission
//     */
//    public URL(String protocol, String host, int port, String file, URLStreamHandler handler) throws MalformedURLException {
//        if (handler != null) {
//            SecurityManager sm = System.getSecurityManager();
//            if (sm != null) {
//                // check for permission to specify a handler
//                checkSpecifyHandler(sm);
//            }
//        }
//
//        protocol = protocol.toLowerCase();
//        this.protocol = protocol;
//        if (host != null) {
//
//            /**
//             * if host is a literal IPv6 address,
//             * we will make it conform to RFC 2732
//             */
//            if (host.indexOf(':') >= 0 && !host.startsWith("[")) {
//                host = "["+host+"]";
//            }
//            this.host = host;
//
//            if (port < -1) {
//                throw new MalformedURLException("Invalid port number :" +
//                                                    port);
//            }
//            this.port = port;
//            authority = (port == -1) ? host : host + ":" + port;
//        }
//
//        Parts parts = new Parts(file);
//        path = parts.getPath();
//        query = parts.getQuery();
//
//        if (query != null) {
//            this.file = path + "?" + query;
//        } else {
//            this.file = path;
//        }
//        ref = parts.getRef();
//
//        // Note: we don't do validation of the URL here. Too risky to change
//        // right now, but worth considering for future reference. -br
//        if (handler == null &&
//            (handler = getURLStreamHandler(protocol)) == null) {
//            throw new MalformedURLException("unknown protocol: " + protocol);
//        }
//        this.handler = handler;
//    }
//
//    
//
//    /**
//     * Creates a URL by parsing the given spec within a specified context.
//     *
//     * The new URL is created from the given context URL and the spec
//     * argument as described in
//     * RFC2396 &quot;Uniform Resource Identifiers : Generic * Syntax&quot; :
//     * <blockquote><pre>
//     *          &lt;scheme&gt;://&lt;authority&gt;&lt;path&gt;?&lt;query&gt;#&lt;fragment&gt;
//     * </pre></blockquote>
//     * The reference is parsed into the scheme, authority, path, query and
//     * fragment parts. If the path component is empty and the scheme,
//     * authority, and query components are undefined, then the new URL is a
//     * reference to the current document. Otherwise, the fragment and query
//     * parts present in the spec are used in the new URL.
//     * <p>
//     * If the scheme component is defined in the given spec and does not match
//     * the scheme of the context, then the new URL is created as an absolute
//     * URL based on the spec alone. Otherwise the scheme component is inherited
//     * from the context URL.
//     * <p>
//     * If the authority component is present in the spec then the spec is
//     * treated as absolute and the spec authority and path will replace the
//     * context authority and path. If the authority component is absent in the
//     * spec then the authority of the new URL will be inherited from the
//     * context.
//     * <p>
//     * If the spec's path component begins with a slash character
//     * &quot;/&quot; then the
//     * path is treated as absolute and the spec path replaces the context path.
//     * <p>
//     * Otherwise, the path is treated as a relative path and is appended to the
//     * context path, as described in RFC2396. Also, in this case,
//     * the path is canonicalized through the removal of directory
//     * changes made by occurrences of &quot;..&quot; and &quot;.&quot;.
//     * <p>
//     * For a more detailed description of URL parsing, refer to RFC2396.
//     *
//     * @param      context   the context in which to parse the specification.
//     * @param      spec      the {@code String} to parse as a URL.
//     * @exception  MalformedURLException  if no protocol is specified, or an
//     *               unknown protocol is found, or {@code spec} is {@code null}.
//     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
//     *                  int, java.lang.String)
//     * @see        java.net.URLStreamHandler
//     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,
//     *                  java.lang.String, int, int)
//     */
//    public URL(URL context, String spec) throws MalformedURLException {
//        this(context, spec, null);
//    }
//
//    /**
//     * Creates a URL by parsing the given spec with the specified handler
//     * within a specified context. If the handler is null, the parsing
//     * occurs as with the two argument constructor.
//     *
//     * @param      context   the context in which to parse the specification.
//     * @param      spec      the {@code String} to parse as a URL.
//     * @param      handler   the stream handler for the URL.
//     * @exception  MalformedURLException  if no protocol is specified, or an
//     *               unknown protocol is found, or {@code spec} is {@code null}.
//     * @exception  SecurityException
//     *        if a security manager exists and its
//     *        {@code checkPermission} method doesn't allow
//     *        specifying a stream handler.
//     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
//     *                  int, java.lang.String)
//     * @see        java.net.URLStreamHandler
//     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,
//     *                  java.lang.String, int, int)
//     */
//    public URL(URL context, String spec, URLStreamHandler handler)
//        throws MalformedURLException
//    {
//        String original = spec;
//        int i, limit, c;
//        int start = 0;
//        String newProtocol = null;
//        boolean aRef=false;
//        boolean isRelative = false;
//
//        // Check for permission to specify a handler
//        if (handler != null) {
//            SecurityManager sm = System.getSecurityManager();
//            if (sm != null) {
//                checkSpecifyHandler(sm);
//            }
//        }
//
//        try {
//            limit = spec.length();
//            while ((limit > 0) && (spec.charAt(limit - 1) <= ' ')) {
//                limit--;        //eliminate trailing whitespace
//            }
//            while ((start < limit) && (spec.charAt(start) <= ' ')) {
//                start++;        // eliminate leading whitespace
//            }
//
//            if (spec.regionMatches(true, start, "url:", 0, 4)) {
//                start += 4;
//            }
//            if (start < spec.length() && spec.charAt(start) == '#') {
//                /* we're assuming this is a ref relative to the context URL.
//                 * This means protocols cannot start w/ '#', but we must parse
//                 * ref URL's like: "hello:there" w/ a ':' in them.
//                 */
//                aRef=true;
//            }
//            for (i = start ; !aRef && (i < limit) &&
//                     ((c = spec.charAt(i)) != '/') ; i++) {
//                if (c == ':') {
//
//                    String s = spec.substring(start, i).toLowerCase();
//                    if (isValidProtocol(s)) {
//                        newProtocol = s;
//                        start = i + 1;
//                    }
//                    break;
//                }
//            }
//
//            // Only use our context if the protocols match.
//            protocol = newProtocol;
//            if ((context != null) && ((newProtocol == null) ||
//                            newProtocol.equalsIgnoreCase(context.protocol))) {
//                // inherit the protocol handler from the context
//                // if not specified to the constructor
//                if (handler == null) {
//                    handler = context.handler;
//                }
//
//                // If the context is a hierarchical URL scheme and the spec
//                // contains a matching scheme then maintain backwards
//                // compatibility and treat it as if the spec didn't contain
//                // the scheme; see 5.2.3 of RFC2396
//                if (context.path != null && context.path.startsWith("/"))
//                    newProtocol = null;
//
//                if (newProtocol == null) {
//                    protocol = context.protocol;
//                    authority = context.authority;
//                    userInfo = context.userInfo;
//                    host = context.host;
//                    port = context.port;
//                    file = context.file;
//                    path = context.path;
//                    isRelative = true;
//                }
//            }
//
//            if (protocol == null) {
//                throw new MalformedURLException("no protocol: "+original);
//            }
//
//            // Get the protocol handler if not specified or the protocol
//            // of the context could not be used
//            if (handler == null &&
//                (handler = getURLStreamHandler(protocol)) == null) {
//                throw new MalformedURLException("unknown protocol: "+protocol);
//            }
//
//            this.handler = handler;
//
//            i = spec.indexOf('#', start);
//            if (i >= 0) {
//                ref = spec.substring(i + 1, limit);
//                limit = i;
//            }
//
//            /*
//             * Handle special case inheritance of query and fragment
//             * implied by RFC2396 section 5.2.2.
//             */
//            if (isRelative && start == limit) {
//                query = context.query;
//                if (ref == null) {
//                    ref = context.ref;
//                }
//            }
//
//            handler.parseURL(this, spec, start, limit);
//
//        } catch(MalformedURLException e) {
//            throw e;
//        } catch(Exception e) {
//            MalformedURLException exception = new MalformedURLException(e.getMessage());
//            exception.initCause(e);
//            throw exception;
//        }
//    }
//
//   
//
//
//
//   
//
//    /**
//     * Opens a connection to this {@code URL} and returns an
//     * {@code InputStream} for reading from that connection. This
//     * method is a shorthand for:
//     * <blockquote><pre>
//     *     openConnection().getInputStream()
//     * </pre></blockquote>
//     *
//     * @return     an input stream for reading from the URL connection.
//     * @exception  IOException  if an I/O exception occurs.
//     * @see        java.net.URL#openConnection()
//     * @see        java.net.URLConnection#getInputStream()
//     */
//    public final InputStream openStream() throws java.io.IOException {
//        return openConnection().getInputStream();
//    }
//
//    /**
//     * Gets the contents of this URL. This method is a shorthand for:
//     * <blockquote><pre>
//     *     openConnection().getContent()
//     * </pre></blockquote>
//     *
//     * @return     the contents of this URL.
//     * @exception  IOException  if an I/O exception occurs.
//     * @see        java.net.URLConnection#getContent()
//     */
//    public final Object getContent() throws java.io.IOException {
//        return openConnection().getContent();
//    }
//
//    /**
//     * Gets the contents of this URL. This method is a shorthand for:
//     * <blockquote><pre>
//     *     openConnection().getContent(Class[])
//     * </pre></blockquote>
//     *
//     * @param classes an array of Java types
//     * @return     the content object of this URL that is the first match of
//     *               the types specified in the classes array.
//     *               null if none of the requested types are supported.
//     * @exception  IOException  if an I/O exception occurs.
//     * @see        java.net.URLConnection#getContent(Class[])
//     * @since 1.3
//     */
//    public final Object getContent(Class[] classes) throws java.io.IOException {
//        return openConnection().getContent(classes);
//    }
//
//    /**
//     * Sets an application's {@code URLStreamHandlerFactory}.
//     * This method can be called at most once in a given Java Virtual
//     * Machine.
//     *
//     *<p> The {@code URLStreamHandlerFactory} instance is used to
//     *construct a stream protocol handler from a protocol name.
//     *
//     * <p> If there is a security manager, this method first calls
//     * the security manager's {@code checkSetFactory} method
//     * to ensure the operation is allowed.
//     * This could result in a SecurityException.
//     *
//     * @param      fac   the desired factory.
//     * @exception  Error  if the application has already set a factory.
//     * @exception  SecurityException  if a security manager exists and its
//     *             {@code checkSetFactory} method doesn't allow
//     *             the operation.
//     * @see        java.net.URL#URL(java.lang.String, java.lang.String,
//     *             int, java.lang.String)
//     * @see        java.net.URLStreamHandlerFactory
//     * @see        SecurityManager#checkSetFactory
//     */
//    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory fac) {
//        synchronized (streamHandlerLock) {
//            if (factory != null) {
//                throw new Error("factory already defined");
//            }
//            SecurityManager security = System.getSecurityManager();
//            if (security != null) {
//                security.checkSetFactory();
//            }
//            handlers.clear();
//            factory = fac;
//        }
//    }
//
//}
//
