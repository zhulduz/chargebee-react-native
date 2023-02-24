import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import * as eva from '@eva-design/eva';
import { ApplicationProvider } from '@ui-kitten/components';
import { default as theme } from './theme.json';

import Chargebee from '@chargebee/react-native-chargebee';

import LoginScreen from './screens/LoginScreen';
import HomeScreen from './screens/HomeScreen';
import ProductDetail from './screens/ProductDetail';

const Stack = createNativeStackNavigator();

export default function App() {
  const site = 'site';
  const apiKey = 'apiKey';
  const iOsSdkKey = 'iOsSdkKey';
  const androidSdkKey = 'androidSdkKey';

  useEffect(() => {
    Chargebee.configure({
      site: site,
      publishableApiKey: apiKey,
      androidSdkKey: androidSdkKey,
      iOsSdkKey: iOsSdkKey,
    });
    console.debug('Configured Chargebee SDK');
  });

  return (
    <ApplicationProvider {...eva} theme={{ ...eva.light, ...theme }}>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
            name="Login"
            component={LoginScreen}
            options={{ title: 'Login' }}
          />
          <Stack.Screen
            name="Home"
            component={HomeScreen}
            options={{ title: 'Hello' }}
          />
          <Stack.Screen
            name="ProductDetail"
            component={ProductDetail}
            options={{ title: 'Product Detail' }}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </ApplicationProvider>
  );
}
