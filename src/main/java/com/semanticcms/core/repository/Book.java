/*
 * semanticcms-core-repository - Redistributable sets of SemanticCMS pages and associated resources.
 * Copyright (C) 2014, 2015, 2016, 2017  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-core-repository.
 *
 * semanticcms-core-repository is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-core-repository is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-core-repository.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.core.repository;

import com.aoindustries.lang.NullArgumentException;
import com.semanticcms.core.model.Author;
import com.semanticcms.core.model.BookRef;
import com.semanticcms.core.model.Copyright;
import com.semanticcms.core.model.PageRef;
import com.semanticcms.core.model.ParentRef;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * A book contains pages and is the central mechanism for high-level
 * separation of content.  Each book usually has its own code repository
 * and a book can be added to multiple webapps.
 *
 * TODO: Interface + abstract base?
 */
abstract public class Book implements Comparable<Book> {

	protected final BookRef bookRef;

	private final String canonicalBase;

	public Book(BookRef bookRef, String canonicalBase) {
		this.bookRef = NullArgumentException.checkNotNull(bookRef, "bookRef");
		this.canonicalBase = canonicalBase;
	}

	/**
	 * @see  BookRef#toString()
	 */
	@Override
	public String toString() {
		return bookRef.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Book)) return false;
		Book other = (Book)obj;
		return bookRef.equals(other.bookRef);
	}

	@Override
	public int hashCode() {
		return bookRef.hashCode();
	}

	/**
	 * Ordered by bookRef.
	 *
	 * @see  BookRef#compareTo(com.semanticcms.core.model.BookRef)
	 */
	@Override
	public int compareTo(Book o) {
		return bookRef.compareTo(o.bookRef);
	}

	public BookRef getBookRef() {
		return bookRef;
	}

	/**
	 * @deprecated  Please use {@link #getBookRef()}.{@link BookRef#getName()}
	 */
	@Deprecated
	public String getName() {
		return bookRef.getName();
	}

	/**
	 * @deprecated  Please use {@link #getBookRef()}.{@link BookRef#getPrefix()}
	 */
	@Deprecated
	public String getPathPrefix() {
		return getBookRef().getPrefix();
	}

	/**
	 * An accessible book is able to invoke/capture its pages and is fully
	 * connected into the page DAG.  Please note that accessible books may not
	 * be local, but they are still part of the set of pages.
	 */
	abstract public boolean isAccessible();

	/**
	 * A book may be able to provide a local file source for a given path.
	 *
	 * TODO: Does this belong here?
	 *
	 * @return  The {@link File} or {@code null} if source not accessible.
	 */
	abstract public File getSourceFile(String path, boolean requireBook, boolean requireFile);

	/**
	 * A book may be able to provide a URL source for a given path.
	 *
	 * TODO: Does this belong here?
	 *
	 * @return  The {@link URL} or {@code null} if source not accessible.
	 */
	abstract public URL getSourceURL(String path);

	/**
	 * Gets the parent pages for this book in the context of the current overall
	 * content.
	 *
	 * @return  The, possibly empty, set of parents for an accessible book
	 *          or {@code null} for an inaccessible book
	 */
	abstract public Set<ParentRef> getParentRefs();

	/**
	 * Gets the configured canonicalBase for this book, or {@code null} if not
	 * configured or inaccessible.  Any trailing slash (/) has been stripped from the canonicalBase
	 * so can directly concatenate canonicalBase + path
	 */
	public String getCanonicalBase() {
		return canonicalBase;
	}

	/**
	 * Gets the content root for the book or {@code null} if inaccessible.
	 */
	abstract public PageRef getContentRoot();

	/**
	 * Gets the copyright for the book or {@code null} if none declared or inaccessible.
	 * As book copyrights are not inherited, all copyright fields will be non-null.
	 */
	abstract public Copyright getCopyright();

	/**
	 * Gets the authors of the book.  Any page without more specific authors
	 * in itself or a parent (within the book) will use these authors.
	 *
	 * @return  The, possibly empty, set of authors for an accessible book
	 *          or {@code null} for an inaccessible book
	 */
	abstract public Set<Author> getAuthors();

	/**
	 * Gets the book's title or {@code null} if none declared or inaccessible.
	 */
	abstract public String getTitle();

	/**
	 * Gets the allowRobots setting of the book.  Any page with an "auto"
	 * setting and no parents within the book will use this setting.
	 * An inaccessible book must return {@code false}.
	 */
	abstract public boolean getAllowRobots();

	/**
	 * Accesses the books parameters.
	 *
	 * TODO: Should this be named "property" to be consistent with per-page properties?
	 *
	 * @return  The, possibly empty, map of parameters for an accessible book
	 *          or {@code null} for an inaccessible book
	 */
	abstract public Map<String,String> getParam();
}
