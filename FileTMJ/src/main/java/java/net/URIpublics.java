//package java.net;
//
///**
// * Represents a Uniform Resource Identifier (URI) reference.
// *
// * <p> Aside from some minor deviations noted below, an instance of this
// * class represents a URI reference as defined by
// * <a href="http://www.ietf.org/rfc/rfc2396.txt"><i>RFC&nbsp;2396: Uniform
// * Resource Identifiers (URI): Generic Syntax</i></a>, amended by <a
// * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
// * Literal IPv6 Addresses in URLs</i></a>. The Literal IPv6 address format
// * also supports scope_ids. The syntax and usage of scope_ids is described
// * <a href="Inet6Address.html#scoped">here</a>.
// * This class provides constructors for creating URI instances from
// * their components or by parsing their string forms, methods for accessing the
// * various components of an instance, and methods for normalizing, resolving,
// * and relativizing URI instances.  Instances of this class are immutable.
// *
// *
// * <h3> URI syntax and components </h3>
// *
// * At the highest level a URI reference (hereinafter simply "URI") in string
// * form has the syntax
// *
// * <blockquote>
// * [<i>scheme</i><b>{@code :}</b>]<i>scheme-specific-part</i>[<b>{@code #}</b><i>fragment</i>]
// * </blockquote>
// *
// * where square brackets [...] delineate optional components and the characters
// * <b>{@code :}</b> and <b>{@code #}</b> stand for themselves.
// *
// * <p> An <i>absolute</i> URI specifies a scheme; a URI that is not absolute is
// * said to be <i>relative</i>.  URIs are also classified according to whether
// * they are <i>opaque</i> or <i>hierarchical</i>.
// *
// * <p> An <i>opaque</i> URI is an absolute URI whose scheme-specific part does
// * not begin with a slash character ({@code '/'}).  Opaque URIs are not
// * subject to further parsing.  Some examples of opaque URIs are:
// *
// * <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
// * <tr><td>{@code mailto:java-net@java.sun.com}<td></tr>
// * <tr><td>{@code news:comp.lang.java}<td></tr>
// * <tr><td>{@code urn:isbn:096139210x}</td></tr>
// * </table></blockquote>
// *
// * <p> A <i>hierarchical</i> URI is either an absolute URI whose
// * scheme-specific part begins with a slash character, or a relative URI, that
// * is, a URI that does not specify a scheme.  Some examples of hierarchical
// * URIs are:
// *
// * <blockquote>
// * {@code http://java.sun.com/j2se/1.3/}<br>
// * {@code docs/guide/collections/designfaq.html#28}<br>
// * {@code ../../../demo/jfc/SwingSet2/src/SwingSet2.java}<br>
// * {@code file:///~/calendar}
// * </blockquote>
// *
// * <p> A hierarchical URI is subject to further parsing according to the syntax
// *
// * <blockquote>
// * [<i>scheme</i><b>{@code :}</b>][<b>{@code //}</b><i>authority</i>][<i>path</i>][<b>{@code ?}</b><i>query</i>][<b>{@code #}</b><i>fragment</i>]
// * </blockquote>
// *
// * where the characters <b>{@code :}</b>, <b>{@code /}</b>,
// * <b>{@code ?}</b>, and <b>{@code #}</b> stand for themselves.  The
// * scheme-specific part of a hierarchical URI consists of the characters
// * between the scheme and fragment components.
// *
// * <p> The authority component of a hierarchical URI is, if specified, either
// * <i>server-based</i> or <i>registry-based</i>.  A server-based authority
// * parses according to the familiar syntax
// *
// * <blockquote>
// * [<i>user-info</i><b>{@code @}</b>]<i>host</i>[<b>{@code :}</b><i>port</i>]
// * </blockquote>
// *
// * where the characters <b>{@code @}</b> and <b>{@code :}</b> stand for
// * themselves.  Nearly all URI schemes currently in use are server-based.  An
// * authority component that does not parse in this way is considered to be
// * registry-based.
// *
// * <p> The path component of a hierarchical URI is itself said to be absolute
// * if it begins with a slash character ({@code '/'}); otherwise it is
// * relative.  The path of a hierarchical URI that is either absolute or
// * specifies an authority is always absolute.
// *
// * <p> All told, then, a URI instance has the following nine components:
// *
// * <blockquote><table summary="Describes the components of a URI:scheme,scheme-specific-part,authority,user-info,host,port,path,query,fragment">
// * <tr><th><i>Component</i></th><th><i>Type</i></th></tr>
// * <tr><td>scheme</td><td>{@code String}</td></tr>
// * <tr><td>scheme-specific-part&nbsp;&nbsp;&nbsp;&nbsp;</td><td>{@code String}</td></tr>
// * <tr><td>authority</td><td>{@code String}</td></tr>
// * <tr><td>user-info</td><td>{@code String}</td></tr>
// * <tr><td>host</td><td>{@code String}</td></tr>
// * <tr><td>port</td><td>{@code int}</td></tr>
// * <tr><td>path</td><td>{@code String}</td></tr>
// * <tr><td>query</td><td>{@code String}</td></tr>
// * <tr><td>fragment</td><td>{@code String}</td></tr>
// * </table></blockquote>
// *
// * In a given instance any particular component is either <i>undefined</i> or
// * <i>defined</i> with a distinct value.  Undefined string components are
// * represented by {@code null}, while undefined integer components are
// * represented by {@code -1}.  A string component may be defined to have the
// * empty string as its value; this is not equivalent to that component being
// * undefined.
// *
// * <p> Whether a particular component is or is not defined in an instance
// * depends upon the type of the URI being represented.  An absolute URI has a
// * scheme component.  An opaque URI has a scheme, a scheme-specific part, and
// * possibly a fragment, but has no other components.  A hierarchical URI always
// * has a path (though it may be empty) and a scheme-specific-part (which at
// * least contains the path), and may have any of the other components.  If the
// * authority component is present and is server-based then the host component
// * will be defined and the user-information and port components may be defined.
// *
// *
// * <h4> Operations on URI instances </h4>
// *
// * The key operations supported by this class are those of
// * <i>normalization</i>, <i>resolution</i>, and <i>relativization</i>.
// *
// * <p> <i>Normalization</i> is the process of removing unnecessary {@code "."}
// * and {@code ".."} segments from the path component of a hierarchical URI.
// * Each {@code "."} segment is simply removed.  A {@code ".."} segment is
// * removed only if it is preceded by a non-{@code ".."} segment.
// * Normalization has no effect upon opaque URIs.
// *
// * <p> <i>Resolution</i> is the process of resolving one URI against another,
// * <i>base</i> URI.  The resulting URI is constructed from components of both
// * URIs in the manner specified by RFC&nbsp;2396, taking components from the
// * base URI for those not specified in the original.  For hierarchical URIs,
// * the path of the original is resolved against the path of the base and then
// * normalized.  The result, for example, of resolving
// *
// * <blockquote>
// * {@code docs/guide/collections/designfaq.html#28}
// * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
// * &nbsp;&nbsp;&nbsp;&nbsp;(1)
// * </blockquote>
// *
// * against the base URI {@code http://java.sun.com/j2se/1.3/} is the result
// * URI
// *
// * <blockquote>
// * {@code https://docs.oracle.com/javase/1.3/docs/guide/collections/designfaq.html#28}
// * </blockquote>
// *
// * Resolving the relative URI
// *
// * <blockquote>
// * {@code ../../../demo/jfc/SwingSet2/src/SwingSet2.java}&nbsp;&nbsp;&nbsp;&nbsp;(2)
// * </blockquote>
// *
// * against this result yields, in turn,
// *
// * <blockquote>
// * {@code http://java.sun.com/j2se/1.3/demo/jfc/SwingSet2/src/SwingSet2.java}
// * </blockquote>
// *
// * Resolution of both absolute and relative URIs, and of both absolute and
// * relative paths in the case of hierarchical URIs, is supported.  Resolving
// * the URI {@code file:///~calendar} against any other URI simply yields the
// * original URI, since it is absolute.  Resolving the relative URI (2) above
// * against the relative base URI (1) yields the normalized, but still relative,
// * URI
// *
// * <blockquote>
// * {@code demo/jfc/SwingSet2/src/SwingSet2.java}
// * </blockquote>
// *
// * <p> <i>Relativization</i>, finally, is the inverse of resolution: For any
// * two normalized URIs <i>u</i> and&nbsp;<i>v</i>,
// *
// * <blockquote>
// *   <i>u</i>{@code .relativize(}<i>u</i>{@code .resolve(}<i>v</i>{@code )).equals(}<i>v</i>{@code )}&nbsp;&nbsp;and<br>
// *   <i>u</i>{@code .resolve(}<i>u</i>{@code .relativize(}<i>v</i>{@code )).equals(}<i>v</i>{@code )}&nbsp;&nbsp;.<br>
// * </blockquote>
// *
// * This operation is often useful when constructing a document containing URIs
// * that must be made relative to the base URI of the document wherever
// * possible.  For example, relativizing the URI
// *
// * <blockquote>
// * {@code https://docs.oracle.com/javase/1.3/docs/guide/index.html}
// * </blockquote>
// *
// * against the base URI
// *
// * <blockquote>
// * {@code http://java.sun.com/j2se/1.3}
// * </blockquote>
// *
// * yields the relative URI {@code docs/guide/index.html}.
// *
// *
// * <h4> Character categories </h4>
// *
// * RFC&nbsp;2396 specifies precisely which characters are permitted in the
// * various components of a URI reference.  The following categories, most of
// * which are taken from that specification, are used below to describe these
// * constraints:
// *
// * <blockquote><table cellspacing=2 summary="Describes categories alpha,digit,alphanum,unreserved,punct,reserved,escaped,and other">
// *   <tr><th valign=top><i>alpha</i></th>
// *       <td>The US-ASCII alphabetic characters,
// *        {@code 'A'}&nbsp;through&nbsp;{@code 'Z'}
// *        and {@code 'a'}&nbsp;through&nbsp;{@code 'z'}</td></tr>
// *   <tr><th valign=top><i>digit</i></th>
// *       <td>The US-ASCII decimal digit characters,
// *       {@code '0'}&nbsp;through&nbsp;{@code '9'}</td></tr>
// *   <tr><th valign=top><i>alphanum</i></th>
// *       <td>All <i>alpha</i> and <i>digit</i> characters</td></tr>
// *   <tr><th valign=top><i>unreserved</i>&nbsp;&nbsp;&nbsp;&nbsp;</th>
// *       <td>All <i>alphanum</i> characters together with those in the string
// *        {@code "_-!.~'()*"}</td></tr>
// *   <tr><th valign=top><i>punct</i></th>
// *       <td>The characters in the string {@code ",;:$&+="}</td></tr>
// *   <tr><th valign=top><i>reserved</i></th>
// *       <td>All <i>punct</i> characters together with those in the string
// *        {@code "?/[]@"}</td></tr>
// *   <tr><th valign=top><i>escaped</i></th>
// *       <td>Escaped octets, that is, triplets consisting of the percent
// *           character ({@code '%'}) followed by two hexadecimal digits
// *           ({@code '0'}-{@code '9'}, {@code 'A'}-{@code 'F'}, and
// *           {@code 'a'}-{@code 'f'})</td></tr>
// *   <tr><th valign=top><i>other</i></th>
// *       <td>The Unicode characters that are not in the US-ASCII character set,
// *           are not control characters (according to the {@link
// *           java.lang.Character#isISOControl(char) Character.isISOControl}
// *           method), and are not space characters (according to the {@link
// *           java.lang.Character#isSpaceChar(char) Character.isSpaceChar}
// *           method)&nbsp;&nbsp;<i>(<b>Deviation from RFC 2396</b>, which is
// *           limited to US-ASCII)</i></td></tr>
// * </table></blockquote>
// *
// * <p><a name="legal-chars"></a> The set of all legal URI characters consists of
// * the <i>unreserved</i>, <i>reserved</i>, <i>escaped</i>, and <i>other</i>
// * characters.
// *
// *
// * <h4> Escaped octets, quotation, encoding, and decoding </h4>
// *
// * RFC 2396 allows escaped octets to appear in the user-info, path, query, and
// * fragment components.  Escaping serves two purposes in URIs:
// *
// * <ul>
// *
// *   <li><p> To <i>encode</i> non-US-ASCII characters when a URI is required to
// *   conform strictly to RFC&nbsp;2396 by not containing any <i>other</i>
// *   characters.  </p></li>
// *
// *   <li><p> To <i>quote</i> characters that are otherwise illegal in a
// *   component.  The user-info, path, query, and fragment components differ
// *   slightly in terms of which characters are considered legal and illegal.
// *   </p></li>
// *
// * </ul>
// *
// * These purposes are served in this class by three related operations:
// *
// * <ul>
// *
// *   <li><p><a name="encode"></a> A character is <i>encoded</i> by replacing it
// *   with the sequence of escaped octets that represent that character in the
// *   UTF-8 character set.  The Euro currency symbol ({@code '\u005Cu20AC'}),
// *   for example, is encoded as {@code "%E2%82%AC"}.  <i>(<b>Deviation from
// *   RFC&nbsp;2396</b>, which does not specify any particular character
// *   set.)</i> </p></li>
// *
// *   <li><p><a name="quote"></a> An illegal character is <i>quoted</i> simply by
// *   encoding it.  The space character, for example, is quoted by replacing it
// *   with {@code "%20"}.  UTF-8 contains US-ASCII, hence for US-ASCII
// *   characters this transformation has exactly the effect required by
// *   RFC&nbsp;2396. </p></li>
// *
// *   <li><p><a name="decode"></a>
// *   A sequence of escaped octets is <i>decoded</i> by
// *   replacing it with the sequence of characters that it represents in the
// *   UTF-8 character set.  UTF-8 contains US-ASCII, hence decoding has the
// *   effect of de-quoting any quoted US-ASCII characters as well as that of
// *   decoding any encoded non-US-ASCII characters.  If a <a
// *   href="../nio/charset/CharsetDecoder.html#ce">decoding error</a> occurs
// *   when decoding the escaped octets then the erroneous octets are replaced by
// *   {@code '\u005CuFFFD'}, the Unicode replacement character.  </p></li>
// *
// * </ul>
// *
// * These operations are exposed in the constructors and methods of this class
// * as follows:
// *
// * <ul>
// *
// *   <li><p> The {@linkplain #URI(java.lang.String) single-argument
// *   constructor} requires any illegal characters in its argument to be
// *   quoted and preserves any escaped octets and <i>other</i> characters that
// *   are present.  </p></li>
// *
// *   <li><p> The {@linkplain
// *   #URI(java.lang.String,java.lang.String,java.lang.String,int,java.lang.String,java.lang.String,java.lang.String)
// *   multi-argument constructors} quote illegal characters as
// *   required by the components in which they appear.  The percent character
// *   ({@code '%'}) is always quoted by these constructors.  Any <i>other</i>
// *   characters are preserved.  </p></li>
// *
// *   <li><p> The {@link #getRawUserInfo() getRawUserInfo}, {@link #getRawPath()
// *   getRawPath}, {@link #getRawQuery() getRawQuery}, {@link #getRawFragment()
// *   getRawFragment}, {@link #getRawAuthority() getRawAuthority}, and {@link
// *   #getRawSchemeSpecificPart() getRawSchemeSpecificPart} methods return the
// *   values of their corresponding components in raw form, without interpreting
// *   any escaped octets.  The strings returned by these methods may contain
// *   both escaped octets and <i>other</i> characters, and will not contain any
// *   illegal characters.  </p></li>
// *
// *   <li><p> The {@link #getUserInfo() getUserInfo}, {@link #getPath()
// *   getPath}, {@link #getQuery() getQuery}, {@link #getFragment()
// *   getFragment}, {@link #getAuthority() getAuthority}, and {@link
// *   #getSchemeSpecificPart() getSchemeSpecificPart} methods decode any escaped
// *   octets in their corresponding components.  The strings returned by these
// *   methods may contain both <i>other</i> characters and illegal characters,
// *   and will not contain any escaped octets.  </p></li>
// *
// *   <li><p> The {@link #toString() toString} method returns a URI string with
// *   all necessary quotation but which may contain <i>other</i> characters.
// *   </p></li>
// *
// *   <li><p> The {@link #toASCIIString() toASCIIString} method returns a fully
// *   quoted and encoded URI string that does not contain any <i>other</i>
// *   characters.  </p></li>
// *
// * </ul>
// *
// *
// * <h4> Identities </h4>
// *
// * For any URI <i>u</i>, it is always the case that
// *
// * <blockquote>
// * {@code new URI(}<i>u</i>{@code .toString()).equals(}<i>u</i>{@code )}&nbsp;.
// * </blockquote>
// *
// * For any URI <i>u</i> that does not contain redundant syntax such as two
// * slashes before an empty authority (as in {@code file:///tmp/}&nbsp;) or a
// * colon following a host name but no port (as in
// * {@code http://java.sun.com:}&nbsp;), and that does not encode characters
// * except those that must be quoted, the following identities also hold:
// * <pre>
// *     new URI(<i>u</i>.getScheme(),
// *             <i>u</i>.getSchemeSpecificPart(),
// *             <i>u</i>.getFragment())
// *     .equals(<i>u</i>)</pre>
// * in all cases,
// * <pre>
// *     new URI(<i>u</i>.getScheme(),
// *             <i>u</i>.getUserInfo(), <i>u</i>.getAuthority(),
// *             <i>u</i>.getPath(), <i>u</i>.getQuery(),
// *             <i>u</i>.getFragment())
// *     .equals(<i>u</i>)</pre>
// * if <i>u</i> is hierarchical, and
// * <pre>
// *     new URI(<i>u</i>.getScheme(),
// *             <i>u</i>.getUserInfo(), <i>u</i>.getHost(), <i>u</i>.getPort(),
// *             <i>u</i>.getPath(), <i>u</i>.getQuery(),
// *             <i>u</i>.getFragment())
// *     .equals(<i>u</i>)</pre>
// * if <i>u</i> is hierarchical and has either no authority or a server-based
// * authority.
// *
// *
// * <h4> URIs, URLs, and URNs </h4>
// *
// * A URI is a uniform resource <i>identifier</i> while a URL is a uniform
// * resource <i>locator</i>.  Hence every URL is a URI, abstractly speaking, but
// * not every URI is a URL.  This is because there is another subcategory of
// * URIs, uniform resource <i>names</i> (URNs), which name resources but do not
// * specify how to locate them.  The {@code mailto}, {@code news}, and
// * {@code isbn} URIs shown above are examples of URNs.
// *
// * <p> The conceptual distinction between URIs and URLs is reflected in the
// * differences between this class and the {@link URL} class.
// *
// * <p> An instance of this class represents a URI reference in the syntactic
// * sense defined by RFC&nbsp;2396.  A URI may be either absolute or relative.
// * A URI string is parsed according to the generic syntax without regard to the
// * scheme, if any, that it specifies.  No lookup of the host, if any, is
// * performed, and no scheme-dependent stream handler is constructed.  Equality,
// * hashing, and comparison are defined strictly in terms of the character
// * content of the instance.  In other words, a URI instance is little more than
// * a structured string that supports the syntactic, scheme-independent
// * operations of comparison, normalization, resolution, and relativization.
// *
// * <p> An instance of the {@link URL} class, by contrast, represents the
// * syntactic components of a URL together with some of the information required
// * to access the resource that it describes.  A URL must be absolute, that is,
// * it must always specify a scheme.  A URL string is parsed according to its
// * scheme.  A stream handler is always established for a URL, and in fact it is
// * impossible to create a URL instance for a scheme for which no handler is
// * available.  Equality and hashing depend upon both the scheme and the
// * Internet address of the host, if any; comparison is not defined.  In other
// * words, a URL is a structured string that supports the syntactic operation of
// * resolution as well as the network I/O operations of looking up the host and
// * opening a connection to the specified resource.
// *
// *
// * @author Mark Reinhold
// * @since 1.4
// *
// * @see <a href="http://www.ietf.org/rfc/rfc2279.txt"><i>RFC&nbsp;2279: UTF-8, a
// * transformation format of ISO 10646</i></a>, <br><a
// * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IPv6 Addressing
// * Architecture</i></a>, <br><a
// * href="http://www.ietf.org/rfc/rfc2396.txt"><i>RFC&nbsp;2396: Uniform
// * Resource Identifiers (URI): Generic Syntax</i></a>, <br><a
// * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
// * Literal IPv6 Addresses in URLs</i></a>, <br><a
// * href="URISyntaxException.html">URISyntaxException</a>
// */
//
//public final class URI {
//    // -- Constructors and factories --
//
//    private URI() { }                           // Used internally
//
//    /**
//     * Constructs a URI by parsing the given string.
//     *
//     * <p> This constructor parses the given string exactly as specified by the
//     * grammar in <a
//     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
//     * Appendix&nbsp;A, <b><i>except for the following deviations:</i></b> </p>
//     *
//     * <ul>
//     *
//     *   <li><p> An empty authority component is permitted as long as it is
//     *   followed by a non-empty path, a query component, or a fragment
//     *   component.  This allows the parsing of URIs such as
//     *   {@code "file:///foo/bar"}, which seems to be the intent of
//     *   RFC&nbsp;2396 although the grammar does not permit it.  If the
//     *   authority component is empty then the user-information, host, and port
//     *   components are undefined. </p></li>
//     *
//     *   <li><p> Empty relative paths are permitted; this seems to be the
//     *   intent of RFC&nbsp;2396 although the grammar does not permit it.  The
//     *   primary consequence of this deviation is that a standalone fragment
//     *   such as {@code "#foo"} parses as a relative URI with an empty path
//     *   and the given fragment, and can be usefully <a
//     *   href="#resolve-frag">resolved</a> against a base URI.
//     *
//     *   <li><p> IPv4 addresses in host components are parsed rigorously, as
//     *   specified by <a
//     *   href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>: Each
//     *   element of a dotted-quad address must contain no more than three
//     *   decimal digits.  Each element is further constrained to have a value
//     *   no greater than 255. </p></li>
//     *
//     *   <li> <p> Hostnames in host components that comprise only a single
//     *   domain label are permitted to start with an <i>alphanum</i>
//     *   character. This seems to be the intent of <a
//     *   href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>
//     *   section&nbsp;3.2.2 although the grammar does not permit it. The
//     *   consequence of this deviation is that the authority component of a
//     *   hierarchical URI such as {@code s://123}, will parse as a server-based
//     *   authority. </p></li>
//     *
//     *   <li><p> IPv6 addresses are permitted for the host component.  An IPv6
//     *   address must be enclosed in square brackets ({@code '['} and
//     *   {@code ']'}) as specified by <a
//     *   href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>.  The
//     *   IPv6 address itself must parse according to <a
//     *   href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</a>.  IPv6
//     *   addresses are further constrained to describe no more than sixteen
//     *   bytes of address information, a constraint implicit in RFC&nbsp;2373
//     *   but not expressible in the grammar. </p></li>
//     *
//     *   <li><p> Characters in the <i>other</i> category are permitted wherever
//     *   RFC&nbsp;2396 permits <i>escaped</i> octets, that is, in the
//     *   user-information, path, query, and fragment components, as well as in
//     *   the authority component if the authority is registry-based.  This
//     *   allows URIs to contain Unicode characters beyond those in the US-ASCII
//     *   character set. </p></li>
//     *
//     * </ul>
//     *
//     * @param  str   The string to be parsed into a URI
//     *
//     * @throws  NullPointerException
//     *          If {@code str} is {@code null}
//     *
//     * @throws  URISyntaxException
//     *          If the given string violates RFC&nbsp;2396, as augmented
//     *          by the above deviations
//     */
//    public URI(String str) throws URISyntaxException {
//        new Parser(str).parse(false);
//    }
//
//    /**
//     * Constructs a hierarchical URI from the given components.
//     *
//     * <p> If a scheme is given then the path, if also given, must either be
//     * empty or begin with a slash character ({@code '/'}).  Otherwise a
//     * component of the new URI may be left undefined by passing {@code null}
//     * for the corresponding parameter or, in the case of the {@code port}
//     * parameter, by passing {@code -1}.
//     *
//     * <p> This constructor first builds a URI string from the given components
//     * according to the rules specified in <a
//     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
//     * section&nbsp;5.2, step&nbsp;7: </p>
//     *
//     * <ol>
//     *
//     *   <li><p> Initially, the result string is empty. </p></li>
//     *
//     *   <li><p> If a scheme is given then it is appended to the result,
//     *   followed by a colon character ({@code ':'}).  </p></li>
//     *
//     *   <li><p> If user information, a host, or a port are given then the
//     *   string {@code "//"} is appended.  </p></li>
//     *
//     *   <li><p> If user information is given then it is appended, followed by
//     *   a commercial-at character ({@code '@'}).  Any character not in the
//     *   <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
//     *   categories is <a href="#quote">quoted</a>.  </p></li>
//     *
//     *   <li><p> If a host is given then it is appended.  If the host is a
//     *   literal IPv6 address but is not enclosed in square brackets
//     *   ({@code '['} and {@code ']'}) then the square brackets are added.
//     *   </p></li>
//     *
//     *   <li><p> If a port number is given then a colon character
//     *   ({@code ':'}) is appended, followed by the port number in decimal.
//     *   </p></li>
//     *
//     *   <li><p> If a path is given then it is appended.  Any character not in
//     *   the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
//     *   categories, and not equal to the slash character ({@code '/'}) or the
//     *   commercial-at character ({@code '@'}), is quoted.  </p></li>
//     *
//     *   <li><p> If a query is given then a question-mark character
//     *   ({@code '?'}) is appended, followed by the query.  Any character that
//     *   is not a <a href="#legal-chars">legal URI character</a> is quoted.
//     *   </p></li>
//     *
//     *   <li><p> Finally, if a fragment is given then a hash character
//     *   ({@code '#'}) is appended, followed by the fragment.  Any character
//     *   that is not a legal URI character is quoted.  </p></li>
//     *
//     * </ol>
//     *
//     * <p> The resulting URI string is then parsed as if by invoking the {@link
//     * #URI(String)} constructor and then invoking the {@link
//     * #parseServerAuthority()} method upon the result; this may cause a {@link
//     * URISyntaxException} to be thrown.  </p>
//     *
//     * @param   scheme    Scheme name
//     * @param   userInfo  User name and authorization information
//     * @param   host      Host name
//     * @param   port      Port number
//     * @param   path      Path
//     * @param   query     Query
//     * @param   fragment  Fragment
//     *
//     * @throws URISyntaxException
//     *         If both a scheme and a path are given but the path is relative,
//     *         if the URI string constructed from the given components violates
//     *         RFC&nbsp;2396, or if the authority component of the string is
//     *         present but cannot be parsed as a server-based authority
//     */
//    public URI(String scheme,
//               String userInfo, String host, int port,
//               String path, String query, String fragment)
//        throws URISyntaxException
//    {
//        String s = toString(scheme, null,
//                            null, userInfo, host, port,
//                            path, query, fragment);
//        checkPath(s, scheme, path);
//        new Parser(s).parse(true);
//    }
//
//    /**
//     * Constructs a hierarchical URI from the given components.
//     *
//     * <p> If a scheme is given then the path, if also given, must either be
//     * empty or begin with a slash character ({@code '/'}).  Otherwise a
//     * component of the new URI may be left undefined by passing {@code null}
//     * for the corresponding parameter.
//     *
//     * <p> This constructor first builds a URI string from the given components
//     * according to the rules specified in <a
//     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
//     * section&nbsp;5.2, step&nbsp;7: </p>
//     *
//     * <ol>
//     *
//     *   <li><p> Initially, the result string is empty.  </p></li>
//     *
//     *   <li><p> If a scheme is given then it is appended to the result,
//     *   followed by a colon character ({@code ':'}).  </p></li>
//     *
//     *   <li><p> If an authority is given then the string {@code "//"} is
//     *   appended, followed by the authority.  If the authority contains a
//     *   literal IPv6 address then the address must be enclosed in square
//     *   brackets ({@code '['} and {@code ']'}).  Any character not in the
//     *   <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
//     *   categories, and not equal to the commercial-at character
//     *   ({@code '@'}), is <a href="#quote">quoted</a>.  </p></li>
//     *
//     *   <li><p> If a path is given then it is appended.  Any character not in
//     *   the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
//     *   categories, and not equal to the slash character ({@code '/'}) or the
//     *   commercial-at character ({@code '@'}), is quoted.  </p></li>
//     *
//     *   <li><p> If a query is given then a question-mark character
//     *   ({@code '?'}) is appended, followed by the query.  Any character that
//     *   is not a <a href="#legal-chars">legal URI character</a> is quoted.
//     *   </p></li>
//     *
//     *   <li><p> Finally, if a fragment is given then a hash character
//     *   ({@code '#'}) is appended, followed by the fragment.  Any character
//     *   that is not a legal URI character is quoted.  </p></li>
//     *
//     * </ol>
//     *
//     * <p> The resulting URI string is then parsed as if by invoking the {@link
//     * #URI(String)} constructor and then invoking the {@link
//     * #parseServerAuthority()} method upon the result; this may cause a {@link
//     * URISyntaxException} to be thrown.  </p>
//     *
//     * @param   scheme     Scheme name
//     * @param   authority  Authority
//     * @param   path       Path
//     * @param   query      Query
//     * @param   fragment   Fragment
//     *
//     * @throws URISyntaxException
//     *         If both a scheme and a path are given but the path is relative,
//     *         if the URI string constructed from the given components violates
//     *         RFC&nbsp;2396, or if the authority component of the string is
//     *         present but cannot be parsed as a server-based authority
//     */
//    public URI(String scheme,
//               String authority,
//               String path, String query, String fragment)
//        throws URISyntaxException
//    {
//        String s = toString(scheme, null,
//                            authority, null, null, -1,
//                            path, query, fragment);
//        checkPath(s, scheme, path);
//        new Parser(s).parse(false);
//    }
//
//    /**
//     * Constructs a hierarchical URI from the given components.
//     *
//     * <p> A component may be left undefined by passing {@code null}.
//     *
//     * <p> This convenience constructor works as if by invoking the
//     * seven-argument constructor as follows:
//     *
//     * <blockquote>
//     * {@code new} {@link #URI(String, String, String, int, String, String, String)
//     * URI}{@code (scheme, null, host, -1, path, null, fragment);}
//     * </blockquote>
//     *
//     * @param   scheme    Scheme name
//     * @param   host      Host name
//     * @param   path      Path
//     * @param   fragment  Fragment
//     *
//     * @throws  URISyntaxException
//     *          If the URI string constructed from the given components
//     *          violates RFC&nbsp;2396
//     */
//    public URI(String scheme, String host, String path, String fragment)
//        throws URISyntaxException
//    {
//        this(scheme, null, host, -1, path, null, fragment);
//    }
//
//    /**
//     * Constructs a URI from the given components.
//     *
//     * <p> A component may be left undefined by passing {@code null}.
//     *
//     * <p> This constructor first builds a URI in string form using the given
//     * components as follows:  </p>
//     *
//     * <ol>
//     *
//     *   <li><p> Initially, the result string is empty.  </p></li>
//     *
//     *   <li><p> If a scheme is given then it is appended to the result,
//     *   followed by a colon character ({@code ':'}).  </p></li>
//     *
//     *   <li><p> If a scheme-specific part is given then it is appended.  Any
//     *   character that is not a <a href="#legal-chars">legal URI character</a>
//     *   is <a href="#quote">quoted</a>.  </p></li>
//     *
//     *   <li><p> Finally, if a fragment is given then a hash character
//     *   ({@code '#'}) is appended to the string, followed by the fragment.
//     *   Any character that is not a legal URI character is quoted.  </p></li>
//     *
//     * </ol>
//     *
//     * <p> The resulting URI string is then parsed in order to create the new
//     * URI instance as if by invoking the {@link #URI(String)} constructor;
//     * this may cause a {@link URISyntaxException} to be thrown.  </p>
//     *
//     * @param   scheme    Scheme name
//     * @param   ssp       Scheme-specific part
//     * @param   fragment  Fragment
//     *
//     * @throws  URISyntaxException
//     *          If the URI string constructed from the given components
//     *          violates RFC&nbsp;2396
//     */
//    public URI(String scheme, String ssp, String fragment)
//        throws URISyntaxException
//    {
//        new Parser(toString(scheme, ssp,
//                            null, null, null, -1,
//                            null, null, fragment))
//            .parse(false);
//    }
//
//    /**
//     * Creates a URI by parsing the given string.
//     *
//     * <p> This convenience factory method works as if by invoking the {@link
//     * #URI(String)} constructor; any {@link URISyntaxException} thrown by the
//     * constructor is caught and wrapped in a new {@link
//     * IllegalArgumentException} object, which is then thrown.
//     *
//     * <p> This method is provided for use in situations where it is known that
//     * the given string is a legal URI, for example for URI constants declared
//     * within in a program, and so it would be considered a programming error
//     * for the string not to parse as such.  The constructors, which throw
//     * {@link URISyntaxException} directly, should be used situations where a
//     * URI is being constructed from user input or from some other source that
//     * may be prone to errors.  </p>
//     *
//     * @param  str   The string to be parsed into a URI
//     * @return The new URI
//     *
//     * @throws  NullPointerException
//     *          If {@code str} is {@code null}
//     *
//     * @throws  IllegalArgumentException
//     *          If the given string violates RFC&nbsp;2396
//     */
//    public static URI create(String str) {
//        try {
//            return new URI(str);
//        } catch (URISyntaxException x) {
//            throw new IllegalArgumentException(x.getMessage(), x);
//        }
//    }
//
//
//    // -- Operations --
//
//    /**
//     * Attempts to parse this URI's authority component, if defined, into
//     * user-information, host, and port components.
//     *
//     * <p> If this URI's authority component has already been recognized as
//     * being server-based then it will already have been parsed into
//     * user-information, host, and port components.  In this case, or if this
//     * URI has no authority component, this method simply returns this URI.
//     *
//     * <p> Otherwise this method attempts once more to parse the authority
//     * component into user-information, host, and port components, and throws
//     * an exception describing why the authority component could not be parsed
//     * in that way.
//     *
//     * <p> This method is provided because the generic URI syntax specified in
//     * <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>
//     * cannot always distinguish a malformed server-based authority from a
//     * legitimate registry-based authority.  It must therefore treat some
//     * instances of the former as instances of the latter.  The authority
//     * component in the URI string {@code "//foo:bar"}, for example, is not a
//     * legal server-based authority but it is legal as a registry-based
//     * authority.
//     *
//     * <p> In many common situations, for example when working URIs that are
//     * known to be either URNs or URLs, the hierarchical URIs being used will
//     * always be server-based.  They therefore must either be parsed as such or
//     * treated as an error.  In these cases a statement such as
//     *
//     * <blockquote>
//     * {@code URI }<i>u</i>{@code  = new URI(str).parseServerAuthority();}
//     * </blockquote>
//     *
//     * <p> can be used to ensure that <i>u</i> always refers to a URI that, if
//     * it has an authority component, has a server-based authority with proper
//     * user-information, host, and port components.  Invoking this method also
//     * ensures that if the authority could not be parsed in that way then an
//     * appropriate diagnostic message can be issued based upon the exception
//     * that is thrown. </p>
//     *
//     * @return  A URI whose authority field has been parsed
//     *          as a server-based authority
//     *
//     * @throws  URISyntaxException
//     *          If the authority component of this URI is defined
//     *          but cannot be parsed as a server-based authority
//     *          according to RFC&nbsp;2396
//     */
//    public URI parseServerAuthority()
//        throws URISyntaxException
//    {
//        // We could be clever and cache the error message and index from the
//        // exception thrown during the original parse, but that would require
//        // either more fields or a more-obscure representation.
//        if ((host != null) || (authority == null))
//            return this;
//        defineString();
//        new Parser(string).parse(true);
//        return this;
//    }
//
//  
//
//
//
//   
//
//    // -- Component access methods --
//
//    /**
//     * Returns the scheme component of this URI.
//     *
//     * <p> The scheme component of a URI, if defined, only contains characters
//     * in the <i>alphanum</i> category and in the string {@code "-.+"}.  A
//     * scheme always starts with an <i>alpha</i> character. <p>
//     *
//     * The scheme component of a URI cannot contain escaped octets, hence this
//     * method does not perform any decoding.
//     *
//     * @return  The scheme component of this URI,
//     *          or {@code null} if the scheme is undefined
//     */
//    public String getScheme() {
//        return scheme;
//    }
//
//
//
//    // -- Equality, comparison, hash code, toString, and serialization --
//
//
//   
//
//    
//
//
//}
