/*
 * semanticcms-core-pages - Redistributable sets of SemanticCMS pages.
 * Copyright (C) 2017, 2018, 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-core-pages.
 *
 * semanticcms-core-pages is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-core-pages is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-core-pages.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.core.pages;

import com.aoapps.net.Path;
import com.semanticcms.core.model.Page;
import java.io.IOException;

/**
 * Gets {@link Page pages} given their paths.
 * <p>
 * When needing to refer to a set of pages with a singular noun, use "repository",
 * which is contrasted with "store" used for <code>Resources</code>.
 * </p>
 *
 * @see  Page
 */
public interface PageRepository {

	/**
	 * Repositories should provide a meaningful toString implementation, which makes
	 * sense with the path following.
	 */
	@Override
	String toString();

	/**
	 * Checks if the repository is currently available.  A repository that is
	 * unavailable is likely going to throw exceptions.  Tools are encouraged
	 * to handle unavailable repositories gracefully, when possible.
	 */
	boolean isAvailable();

	/**
	 * Gets a {@link Page} for the given path.
	 *
	 * @param path   Must be a {@link Path valid path}
	 * @param level  The page capture level or {@code null} when not capturing (page will be used for direct display)
	 *
	 * @return  the page or {@code null} if does not exist
	 *
	 * @throws  IOException  if I/O error occurs
	 */
	Page getPage(Path path, CaptureLevel level) throws IOException;
}
