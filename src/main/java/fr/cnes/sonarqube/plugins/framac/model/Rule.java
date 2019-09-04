/*
 * This file is part of sonarframac.
 *
 * sonarframac is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sonarframac is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with sonarframac.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.cnes.sonarqube.plugins.framac.model;

/**
 * Class used to unmarshal i-Code xml file.
 *
 * It contains data about defined rule.
 */
public class Rule {
    private String key;
    private String name;
    private String internalKey;
    private String description;
    private String severity;
    private String cardinality;
    private String status;
    private String type;
    private String tag;
    private String remediationFunction;
    private String remediationFunctionBaseEffort;

    public String getKey() {
        return key;
    }
}
