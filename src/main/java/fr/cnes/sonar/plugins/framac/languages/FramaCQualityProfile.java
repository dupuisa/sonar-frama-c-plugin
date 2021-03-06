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
package fr.cnes.sonar.plugins.framac.languages;

import fr.cnes.sonar.plugins.framac.model.Rule;
import fr.cnes.sonar.plugins.framac.model.RulesDefinition;
import fr.cnes.sonar.plugins.framac.model.XmlHandler;
import fr.cnes.sonar.plugins.framac.rules.FramaCRulesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

import java.io.InputStream;

/**
 * Built-in quality profile format since SonarQube 6.6.
 */
public final class FramaCQualityProfile implements BuiltInQualityProfilesDefinition {

    /**
     * Display name for the built-in quality profile.
     **/
    private static final String FRAMA_C_RULES_PROFILE_NAME = "Sonar way";

    /**
     * Allow to create a plugin.
     *
     * @param context Context of the plugin.
     */
    @Override
    public void define(Context context) {
        createBuiltInProfile(context, FramaCLanguage.KEY, FramaCRulesDefinition.PATH_TO_RULES_XML);
    }

    /**
     * Create a built in quality profile for a specific language.
     *
     * @param context  SonarQube context in which create the profile.
     * @param language Language key of the associated profile.
     * @param path     Path to the xml definition of all definedRules.
     */
    private void createBuiltInProfile(final Context context, final String language, final String path) {
        // Create a builder for the definedRules' repository.
        final NewBuiltInQualityProfile defaultProfile =
                context.createBuiltInQualityProfile(FRAMA_C_RULES_PROFILE_NAME, language);


        // Retrieve all defined definedRules.
        final InputStream stream = getClass().getResourceAsStream(path);
        final RulesDefinition rules = (RulesDefinition) XmlHandler.unmarshal(stream, RulesDefinition.class);
        // Activate all Frama-C definedRules.
        for (final Rule rule : rules.getDefinedRules()) {
            defaultProfile.activateRule(FramaCRulesDefinition.getRepositoryKeyForLanguage(), rule.getKey());
        }
        // Save the definedRules profile.
        defaultProfile.setDefault(true);
        defaultProfile.done();
    }
}
