import './globals.css'

export const metadata = {
  title: 'CustomerDB',
  description: 'Customer management',
}

export default function RootLayout({ children }) {
  return (
    <html lang="de">
      <body>{children}</body>
    </html>
  )
}
