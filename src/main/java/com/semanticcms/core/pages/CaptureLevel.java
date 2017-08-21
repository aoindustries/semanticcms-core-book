/*
 * semanticcms-core-pages - Redistributable sets of SemanticCMS pages.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017  AO Industries, Inc.
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

/**
 * The capture modes.
 */
public enum CaptureLevel {

	/**
	 * Captures page meta data only, such as title, copyright, authors, parents, and children.
	 */
	PAGE,

	/**
	 * Captures both page and content meta data.
	 */
	META,

	/**
	 * Captures everything: page meta data, content meta data, and all body content.
	 */
	BODY;
}
